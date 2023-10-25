package exercise.controller;

import org.apache.commons.lang3.StringUtils;
import exercise.util.Security;
import exercise.model.User;
import exercise.util.NamedRoutes;
import java.util.Collections;
import java.util.Objects;

import exercise.repository.UserRepository;
import io.javalin.http.NotFoundResponse;
import io.javalin.http.Context;


public class UsersController {

    public static void build(Context ctx) throws Exception {
        ctx.render("users/build.jte");
    }


    public static void show(Context c) {
        long id = c.pathParamAsClass("id", Long.class).getOrDefault(0L);
        if(Objects.equals(c.cookie("token"), UserRepository.find(id).get().getToken())) {
            c.render("users/show.jte");
        }
        c.render("users/build.jte");
    }

    public static void save(Context context) {

        String firstName = context.formParamAsClass("firstName", String.class).get();
        String lastName = context.formParamAsClass("lastName", String.class).get();
        String email = context.formParamAsClass("email", String.class).get();
        String password = context.formParamAsClass("password", String.class).get();

        String token = Security.generateToken();
        var user = new User(firstName, lastName, email, password, token);
        UserRepository.save(user);

        context.cookie("token", token);
        long id = user.getId();

        context.redirect("/users/" + id);

    }
}
