package exercise.controllers;

import io.javalin.Javalin;
import io.javalin.core.util.JavalinException;
import io.javalin.http.Handler;
import java.util.List;
import java.util.Map;
import io.javalin.core.validation.Validator;
import io.javalin.core.validation.ValidationError;
import io.javalin.core.validation.JavalinValidation;
import org.apache.commons.validator.routines.EmailValidator;
import org.apache.commons.lang3.StringUtils;

import exercise.domain.User;
import exercise.domain.query.QUser;

public final class UserController {

    public static Handler listUsers = ctx -> {

        List<User> users = new QUser()
            .orderBy()
                .id.asc()
            .findList();

        ctx.attribute("users", users);
        ctx.render("users/index.html");
    };

    public static Handler showUser = ctx -> {
        long id = ctx.pathParamAsClass("id", Long.class).getOrDefault(null);

        User user = new QUser()
            .id.equalTo(id)
            .findOne();

        ctx.attribute("user", user);
        ctx.render("users/show.html");
    };

    public static Handler newUser = ctx -> {

        ctx.attribute("errors", Map.of());
        ctx.attribute("user", Map.of());
        ctx.render("users/new.html");
    };

    public static Handler createUser = ctx -> {
        String errorStringMessage = "Строка не должна быть пустой!";
        String errorEmailMessage = "Невалидный email";
        String errorPasswordMessage = "Пароль должен содержать только цифры!";
        String errorPasswordLengthMessage = "Пароль должен содержать не меньше 4 символов!";

        String firstName = ctx.formParamAsClass("firstName", String.class).get();
        String lastName = ctx.formParamAsClass("lastName", String.class).get();
        String email = ctx.formParamAsClass("email", String.class).get();
        String password = ctx.formParamAsClass("password", String.class).get();

        Validator<String> validatorFirstName = ctx.formParamAsClass("firstName", String.class)
                .check(i -> (!i.isEmpty()), errorStringMessage);

        Validator<String> validatorLastName = ctx.formParamAsClass("lastName", String.class)
                .check(i -> (!i.isEmpty()), errorStringMessage);

        Validator<String> validatorEmail = ctx.formParamAsClass("email", String.class)
                .check(i -> EmailValidator.getInstance().isValid(i), errorEmailMessage);

        Validator<String> validatorPassword = ctx.formParamAsClass("password", String.class)
                .check(StringUtils::isNumeric, errorPasswordMessage)
                .check(i -> i.length() >= 4, errorPasswordLengthMessage);

        Map<String, List<ValidationError<?>>> errors = JavalinValidation
                .collectErrors(validatorLastName, validatorFirstName, validatorEmail, validatorPassword);

        if(errors.isEmpty()) {
            User user = new User(firstName, lastName, email, password);
            user.save();
            ctx.sessionAttribute("flash", "Пользователь успешно добавлен!");
            ctx.redirect("/users");
            return;
        }
        User user = new User(firstName, lastName, email, password);
        ctx.status(422);
        ctx.attribute("user", user);
        ctx.attribute("errors", errors);
        ctx.render("users/new.html");
    };
}
