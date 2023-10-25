package exercise;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.Test;
import kong.unirest.HttpResponse;
import kong.unirest.Unirest;
import io.javalin.Javalin;

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

    @Test
    void testCreateUser() throws Exception {
        HttpResponse<String> response = Unirest
            .get(baseUrl + "/users/new")
            .asString();

        assertThat(response.getStatus()).isEqualTo(200);

        HttpResponse<String> responseUser1 = Unirest
            .post(baseUrl + "/users")
            .field("firstName", "First Name")
            .field("lastName", "Last Name")
            .field("email", "some@domain.net")
            .field("password", "password")
            .asEmpty();

        assertThat(responseUser1.getStatus()).isEqualTo(302);
        assertThat(responseUser1.getHeaders().getFirst("Location")).isEqualTo("/users/1");

        HttpResponse<String> responseUser2 = Unirest
            .get(baseUrl + "/users/1")
            .asString();

        String body1 = responseUser2.getBody();
        assertThat(body1.toString()).contains("First Name");
        assertThat(body1.toString()).contains("Last Name");
        assertThat(body1.toString()).contains("some@domain.net");

        HttpResponse<String> responseUser3 = Unirest
            .get(baseUrl + "/users/2")
            .asString();

        HttpResponse<String> responseUser4 = Unirest
            .post(baseUrl + "/users")
            .field("firstName", "First Name1")
            .field("lastName", "Last Name1")
            .field("email", "some1@domain.net")
            .field("password", "password")
            .asEmpty();

        assertThat(responseUser4.getStatus()).isEqualTo(302);
        assertThat(responseUser4.getHeaders().getFirst("Location")).isEqualTo("/users/2");

        HttpResponse<String> responseUser5 = Unirest
            .get(baseUrl + "/users/2")
            .asString();

        String body2 = responseUser5.getBody();
        assertThat(body2.toString()).contains("First Name1");
        assertThat(body2.toString()).contains("Last Name1");
        assertThat(body2.toString()).contains("some1@domain.net");

        HttpResponse<String> responseUser6 = Unirest
            .get(baseUrl + "/users/1")
            .asString();

        String body3 = responseUser6.getBody();
        assertThat(body3.toString()).doesNotContain("First Name");
        assertThat(body3.toString()).doesNotContain("Last Name");
        assertThat(body3.toString()).doesNotContain("some@domain.net");
    }
}
