package exercise;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import kong.unirest.HttpResponse;
import kong.unirest.Unirest;
import io.javalin.Javalin;
import exercise.repository.UserRepository;
import exercise.util.Security;

class AppTest {

    private static Javalin app;
    private static String baseUrl;

    @BeforeAll
    public static void beforeAll() {
        app = App.getApp();
        app.start(0);
        int port = app.port();
        baseUrl = "http://localhost:" + port;
    }

    @AfterAll
    public static void afterAll() {
        app.stop();
    }

    @BeforeEach
    public void clear() {
        UserRepository.clear();
    }

    @Test
    void testRootPage() throws Exception {
        HttpResponse<String> response = Unirest.get(baseUrl + "/").asString();

        assertThat(response.getStatus()).isEqualTo(200);
    }

    @Test
    void testListUsers() throws Exception {
        HttpResponse<String> response = Unirest.get(baseUrl + "/users").asString();

        assertThat(response.getStatus()).isEqualTo(200);
    }

    @Test
    void testBuildUserPage() throws Exception {
        HttpResponse<String> response = Unirest.get(baseUrl + "/users/build").asString();

        assertThat(response.getStatus()).isEqualTo(200);
    }

    @Test
    void testRegisterNewUser1() throws Exception {
        HttpResponse responsePost = Unirest
            .post(baseUrl + "/users")
            .field("firstName", "test")
            .field("lastName", "user")
            .field("email", "  User@gmail.com  ")
            .field("password", "12345678")
            .asEmpty();

        assertThat(responsePost.getStatus()).isEqualTo(302);
        assertThat(responsePost.getHeaders().getFirst("Location")).isEqualTo("/users");

        HttpResponse<String> response = Unirest
            .get(baseUrl + "/users")
            .asString();
        String body = response.getBody();
        assertThat(body).contains("Test User");

        var user = UserRepository.search("Test").get(0);
        assertThat(user.getFirstName()).isEqualTo("Test");
        assertThat(user.getLastName()).isEqualTo("User");
        assertThat(user.getEmail()).isEqualTo("user@gmail.com");
        assertThat(user.getPassword()).isEqualTo(Security.encrypt("12345678"));
    }
}
