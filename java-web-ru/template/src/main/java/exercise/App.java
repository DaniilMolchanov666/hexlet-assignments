package exercise;

import exercise.dto.users.UserPage;
import io.javalin.Javalin;

import java.util.Collections;
import java.util.List;
import io.javalin.http.NotFoundResponse;
import exercise.model.User;
//import exercise.dto.users.UserPage;
//import exercise.dto.users.UsersPage;
//import java.util.Collections;

public final class App {

    // Каждый пользователь представлен объектом класса User
    private static final List<User> USERS = Data.getUsers();

    public static Javalin getApp() {

        var app = Javalin.create(config -> {
            config.plugins.enableDevLogging();
        });

        app.get("/users", ctx -> {
            ctx.render("users/show.jte", Collections.singletonMap("users", USERS));
        });

        app.get("/users/{id}", ctx -> {
            int id = ctx.pathParamAsClass("id", Integer.class).getOrDefault(0);
            UserPage userPage = new UserPage(id, USERS);
            User user = userPage.getUser();

            if (user != null) {
                ctx.render("users/index.jte", Collections.singletonMap("user", user));
            } else {
                ctx.res().sendError(404, "User Not Found!");
            }
        });

        app.get("", ctx -> {
            ctx.render("index.jte");
        });

        return app;
    }

    public static void main(String[] args) {
        Javalin app = getApp();
        app.start(7070);
    }
}
