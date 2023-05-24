package exercise;

import io.javalin.http.Context;
import org.apache.commons.validator.routines.EmailValidator;
import org.junit.jupiter.api.*;

import static org.assertj.core.api.Assertions.as;
import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertAll;

import kong.unirest.HttpResponse;
import kong.unirest.Unirest;
import io.javalin.Javalin;
import io.ebean.DB;

import exercise.domain.User;
import exercise.domain.query.QUser;
import io.ebean.Database;

import java.util.Random;

class AppTest {

    private static Javalin app;
    private static String baseUrl;

    @BeforeAll
    static void start() {
        int port = (int) (Math.random()*10000);
        baseUrl = "http://localhost:8000";
//                "http://localhost:" + port;
        app = App.getApp().start(8000);
    }

    @BeforeEach
    void beforeEach() {
        Database db = DB.getDefault();
        db.truncate("user");
        User existingUser = new User("Wendell", "Legros", "a@a.com", "123456");
        existingUser.save();
    }

    @Test
    void usersAddTest() {
        HttpResponse<String> res = Unirest
                .post(baseUrl + "/users")
                .field("firstName", "Jorka")
                .field("lastName", "Boroda")
                .field("email", "jorka@boroda.ru")
                .field("password", "12345678")
                .asString();

        assertThat(res.getStatus()).isEqualTo(302);

        User u = new QUser()
                .email.eq("jorka@boroda.ru").findOne();

        assertThat(u).isNotNull();
        assertThat(u.getEmail()).isEqualTo("jorka@boroda.ru");
        assertThat(u.getFirstName()).isEqualTo("Jorka");
        assertThat(u.getLastName()).isEqualTo("Boroda");
        assertThat(u.getPassword()).isEqualTo("12345678");

    }

    @Test
    void isValidTest() {
        HttpResponse<String> res = Unirest
                .post(baseUrl + "/users")
                .field("firstName", "Jorka")
                .field("lastName", "Boroda")
                .field("email", "jorka@boroda.ru")
                .field("password", "12345678")
                .asString();
        User u = new QUser().email.eq("jorka@boroda.ru").findOne();
        assert u != null;
        assertThat(u.getFirstName()).isNotEmpty();
        assertThat(u.getLastName()).isNotEmpty();
        assertThat(EmailValidator.getInstance().isValid(u.getEmail())).isTrue();
        assertThat(u.getPassword()).hasSizeGreaterThanOrEqualTo(4);
    }

    @Test
    void isNotValidTest() {
        HttpResponse<String> res = Unirest
                .post(baseUrl + "/users")
                .field("firstName", "")
                .field("lastName", "")
                .field("email", "jorka.boroda.ru")
                .field("password", "123")
                .asString();

        assertThat(res.getBody())
                .contains("Имя не должно быть пустым")
                .contains("Фамилия не должна быть пустой")
                .contains("Должно быть валидным email")
                .contains("Пароль должен содержать не менее 4 символов");

        assertThat(res.getStatus()).isEqualTo(422);

    }

    @Test
    void testUsers() {

        // Выполняем GET запрос на адрес http://localhost:port/users
        HttpResponse<String> response = Unirest
            .get(baseUrl + "/users")
            .asString();
        // Получаем тело ответа
        String content = response.getBody();

        // Проверяем код ответа
        assertThat(response.getStatus()).isEqualTo(200);
        // Проверяем, что страница содержит определенный текст
        assertThat(response.getBody()).contains("Wendell Legros");
    }

//    @Test
//    void testUser() {
//        HttpResponse<String> res = Unirest
//                .get(baseUrl + "/users/" + DB.find(User.class, 1).getId())
//                .asString();
//        assertThat(res.getStatus()).isEqualTo(200);
//        assertThat(res.getBody()).contains("Wendell").contains("Legros");
//    }

    @Test
    void testNewUser() {

        HttpResponse<String> response = Unirest
            .get(baseUrl + "/users/new")
            .asString();

        assertThat(response.getStatus()).isEqualTo(200);
    }

    @AfterAll
    static void stop() {
        app.stop();
    }
}
