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

        List<User> users = List.of("John", "Mark", "Ann");

        // Описываем, что будет происходить при GET запросе на адрес /
        // Метод json() кодирует тело ответа в JSON строку и вызывает метод result()
        // Дополнительно устанавливает в ответе заголовок content type со значением json
        app.get("/users", ctx -> ctx.json(users));

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
