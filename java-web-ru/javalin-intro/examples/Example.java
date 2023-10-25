// Импортируем класс Javalin
import io.javalin.Javalin;

public final class App {

    // Метод возвращает настроенное и готовое к старту приложение Javalin
    public static Javalin getApp() {

        // Создаем приложение
        var app = Javalin.create(config -> {
            // Включаем логгирование при разработке
            config.plugins.enableDevLogging();
        });

        // Описываем, что будет происходить при GET запросе на адрес /
        app.get("/", ctx -> ctx.result("Welcome to Javalin!"));

        // Возвращаем настроенное приложение
        return app;
    }

    public static void main(String[] args) {
        // В методе main() получаем приложение
        Javalin app = getApp();
        // И стартуем его на порту 7070
        app.start(7070);
    }
}
