package exercise.controller;

import java.util.Collections;
import exercise.dto.products.ProductsPage;
import exercise.dto.products.ProductPage;
import exercise.model.Product;
import exercise.repository.ProductsRepository;
import exercise.dto.products.BuildProductPage;
import exercise.util.NamedRoutes;
import io.javalin.http.Context;
import io.javalin.validation.ValidationException;
import io.javalin.http.NotFoundResponse;
import java.sql.SQLException;

public class ProductsController {

    public static void build(Context ctx) {
        var page = new BuildProductPage();
        ctx.render("products/build.jte", Collections.singletonMap("page", page));
    }

    public static void create(Context ctx) throws SQLException {
        try {
            var title = ctx.formParamAsClass("title", String.class)
                .check(value -> !value.isEmpty(), "Название не должно быть пустым")
                .get();
            var price = ctx.formParamAsClass("price", Integer.class)
                .check(value -> value >= 0, "Цена не должна быть отрицательной")
                .get();
            var product = new Product(title, price);
            ProductsRepository.save(product);
            ctx.sessionAttribute("flash", "Товар был успешно создан!");
            ctx.sessionAttribute("flash-type", "success");
            ctx.redirect(NamedRoutes.productsPath());

        } catch (ValidationException e) {
            var title = ctx.formParam("title");
            var price = ctx.formParamAsClass("price", Integer.class).getOrDefault(0);
            var page = new BuildProductPage(title, price, e.getErrors());
            ctx.render("products/build.jte", Collections.singletonMap("page", page)).status(422);
        }
    }

    public static void index(Context ctx) throws SQLException {
        var posts = ProductsRepository.getEntities();
        var page = new ProductsPage(posts);
        page.setFlash(ctx.consumeSessionAttribute("flash"));
        page.setFlashType(ctx.consumeSessionAttribute("flash-type"));
        ctx.render("products/index.jte", Collections.singletonMap("page", page));
    }

    public static void show(Context ctx) throws SQLException {
        var id = ctx.pathParamAsClass("id", Long.class).get();
        var product = ProductsRepository.find(id)
            .orElseThrow(() -> new NotFoundResponse("Product not found"));

        var page = new ProductPage(product);
        ctx.render("products/show.jte", Collections.singletonMap("page", page));
    }
}
