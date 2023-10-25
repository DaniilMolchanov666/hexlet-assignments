package exercise;

import io.javalin.Javalin;

import java.sql.Connection;
import java.sql.DriverManager;

public final class App {

    public static Javalin getApp() {

        Javalin j = Javalin.create(config -> {config.plugins.enableDevLogging();});
        j.get("/welcome", ctx -> ctx.result("Welcome to Hexlet!"));
        return j;
    }

    public static void main(String[] args) {
        Javalin app = getApp();
        app.start(7070);
    }
}
