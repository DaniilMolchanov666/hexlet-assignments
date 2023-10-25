// Импортируем класс Javalin
import io.javalin.Javalin;

public final class App {

    public static Javalin getApp() {

        var app = Javalin.create(config -> {
            config.plugins.enableDevLogging();
        });

        List<User> users = List.of("John", "Mark", "Ann");

        app.get("/users/{number}", ctx -> {
            var userNumber = ctx.pathParamAsClass("id", Integer.class);

            if (userNumber >= users.size() || userNumber < 0) {
                throw new NotFoundResponse("User not found");
            }

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
