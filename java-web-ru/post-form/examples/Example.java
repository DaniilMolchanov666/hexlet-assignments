import io.javalin.Javalin;

public final class App {

    public static Javalin getApp() {

        var app = Javalin.create(config -> {
            config.plugins.enableDevLogging();
        });

        List<String> users = List.of("John", "Mark", "Ann");

        app.get("/users", ctx -> {
            var userNumber = ctx.pathParamAsClass("id", Integer.class);
            var cond = ctx.queryParam("cond");
            List<String> filteredUsers;

            if (term == null) {
                filteredUsers = users;
            } else {
                filteredUsers = users
                    .stream()
                    .filter(u -> u.contains(cond))
                    .toList();
            }

            var page = new UsersPage(users, cond);
            ctx.render("users/index.jte", Collections.singletonMap("page", page));
        });

        return app;
    }

    public static void main(String[] args) {
        Javalin app = getApp();
        app.start(7070);
    }
}
