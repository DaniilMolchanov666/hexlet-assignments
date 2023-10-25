package exercise;

import io.javalin.Javalin;

import java.util.HashMap;
import java.util.List;
import java.util.Collections;
import java.util.Objects;

import exercise.model.User;
import exercise.dto.users.UsersPage;
import exercise.repository.UserRepository;
import io.javalin.validation.ValidationError;
import io.javalin.validation.ValidationException;
import org.apache.commons.lang3.StringUtils;
import exercise.util.Security;


public final class App {
    public static Javalin getApp() {

        var app = Javalin.create(config -> {
            config.plugins.enableDevLogging();
        });

        app.get("/users", ctx -> {
            List<User> users = UserRepository.getEntities();
            var page = new UsersPage(users);
            ctx.render("users/show.jte", Collections.singletonMap("page", page));
        });

        app.get("", ctx -> {
            ctx.render("users/index.jte");
        });

        app.get("/users/build", ctx -> {
            User user = new User();
            ctx.render("users/build.jte", Collections.singletonMap("user", user));
        });

        app.post("/users", ctx -> {

            String errorMessageWithPassword = "Пароль должен содержать только числа " +
                    "и иметь не меньше 4 символов";
            String errorMessageWithFirstName = "Имя не должно быть пустым";
            String errorMessageWithEmail = "Емэйл должен быть валидным";
            String errorMessageWithLastName = "Фамилия не должна быть пустой";

            try{
                String password = ctx.formParamAsClass("password", String.class)
                        .check(v -> v.length() >= 4 && StringUtils.isNumeric(v), errorMessageWithPassword)
                        .get();
                String lastName = StringUtils.capitalize(ctx.formParamAsClass("lastName", String.class)
                                .check(v -> !Objects.equals(v, ""), errorMessageWithLastName)
                        .get()
                        .trim());
                String firstName = StringUtils.capitalize(ctx.formParamAsClass("firstName", String.class)
                        .check(v -> !Objects.equals(v, ""), errorMessageWithFirstName)
                        .get()
                        .trim());
                String email = Objects.requireNonNull(ctx.formParamAsClass("email", String.class)
                        .check(CheckEmail::emailValidator, errorMessageWithEmail))
                        .get()
                        .toLowerCase()
                        .trim();

                User newUser = new User(firstName, lastName, email, password);
                UserRepository.save(newUser);

                UsersPage users = new UsersPage(UserRepository.getEntities());
                ctx.res().setStatus(302);
                ctx.redirect("/users");

            } catch(ValidationException e) {

                User newUser = new User();
                newUser.setErrors(e.getErrors());
                ctx.render("users/build.jte", Collections.singletonMap("user", newUser));
            }
        });

        return app;
    }

    public static void main(String[] args) {
        Javalin app = getApp();
        app.start(7070);
    }
}
