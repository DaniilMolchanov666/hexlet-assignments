// Импортируем класс Javalin
import io.javalin.Javalin;

public final class App {

    public static Javalin getApp() {

        var app = Javalin.create(config -> {
            config.plugins.enableDevLogging();
        });

        List<User> users = List.of("John", "Mark", "Ann");

        app.get("/users", ctx -> {
            var userNumber = ctx.queryParamAsClass("user", Integer.class).getOrDefault(0);
            var user = users.get(userNumber);
            ctx.result(user);
        });

        return app;
    }

    public static void main(String[] args) {
        Javalin app = getApp();
        app.start(7070);
    }
}
