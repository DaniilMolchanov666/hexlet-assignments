package exercise;

import io.javalin.Javalin;
import java.util.List;
import java.util.Map;
import java.util.Objects;

// BEGIN

// END

public final class App {

    private static final List<Map<String, String>> COMPANIES = Data.getCompanies();

    public static Javalin getApp() {

        var app = Javalin.create(config -> {
            config.plugins.enableDevLogging();
        });

        app.get("/companies/{id}", ctx -> {
            int id = ctx.pathParamAsClass("id", Integer.class).get();
            for (Map<String, String> comp: COMPANIES) {
                if(comp.containsValue(id)) {
                    ctx.json(comp);
                }
            }

        });

        app.get("/companies", ctx -> {
            ctx.json(COMPANIES);
        });

        app.get("/", ctx -> {
            ctx.result("open something like (you can change id): /companies/5");
        });

        return app;

    }

    public static void main(String[] args) {
        Javalin app = getApp();
        app.start(7070);
    }
}
