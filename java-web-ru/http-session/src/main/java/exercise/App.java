package exercise;

import io.javalin.Javalin;

import java.util.*;

public final class App {

    private static final List<Map<String, String>> USERS = Data.getUsers();

    public static Javalin getApp() {

        var app = Javalin.create(config -> {
            config.plugins.enableDevLogging();
        });

        app.get("/users", ctx -> {
            ArrayList<Map<String, String>> list = new ArrayList<>();
            String id = ctx.pathParam("id");
            for (Map<String, String> user: USERS) {
                if (Objects.equals(String.valueOf(user.get("id")),
                        String.valueOf(ctx.queryParamAsClass("id", Integer.class).getOrDefault(1)))) {
                    list.add(user);
                }
        }
            ctx.json(list.stream().limit(ctx.queryParamAsClass("per", Integer.class).getOrDefault(1)));
        });

        return app;
    }


    public static void main(String[] args) {
        Javalin app = getApp();
        app.start(7070);
    }
}
