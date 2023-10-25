package exercise;

import exercise.dto.users.UsersPage;
import io.javalin.Javalin;
import java.util.List;
import exercise.model.User;
import exercise.dto.users.UsersPage;
import java.util.Collections;
import org.apache.commons.lang3.StringUtils;

public final class App {

    // Каждый пользователь представлен объектом класса User
    private static final List<User> USERS = Data.getUsers();

    public static Javalin getApp() {

        var app = Javalin.create(config -> {
            config.plugins.enableDevLogging();
        });

//        app.get("/users", ctx -> {
//            UsersPage users = new UsersPage(USERS);
//            ctx.render("users/index.jte", Collections.singletonMap("users", users));
//
//        });

        app.get("/users", ctx -> {
            String term = ctx.queryParam("term");
            if(term == null) {
                UsersPage users = new UsersPage(USERS);
                ctx.render("users/index.jte", Collections.singletonMap("users", users));
                return;
            }
            List<User> newListOfUsers = USERS.stream()
                    .filter(user -> StringUtils.startsWithIgnoreCase(user.getFirstName(), term))
                    .toList();
            UsersPage users = new UsersPage(newListOfUsers);
            ctx.render("users/index.jte", Collections.singletonMap("users", users));

        });

        app.get("/", ctx -> {
            ctx.render("index.jte");
        });

        return app;
    }

    public static void main(String[] args) {
        Javalin app = getApp();
        app.start(7070);
    }
}
