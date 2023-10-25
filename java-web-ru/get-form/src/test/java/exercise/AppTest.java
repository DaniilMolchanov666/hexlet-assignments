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
    void testRootPage() throws Exception {
        HttpResponse<String> response = Unirest.get(baseUrl + "/").asString();
        String content = response.getBody();

        assertThat(response.getStatus()).isEqualTo(200);
    }

    @Test
    void testListUsers() throws Exception {
        HttpResponse<String> response = Unirest.get(baseUrl + "/users").asString();
        String content = response.getBody();

        assertThat(response.getStatus()).isEqualTo(200);
        assertThat(content).contains("Bobbi Wehner");
        assertThat(content).contains("Will Casper");
        assertThat(content).contains("Darryl Ward");
    }

    @Test
    void testListUsers1() throws Exception {
        HttpResponse<String> response = Unirest.get(baseUrl + "/users?term=s").asString();
        String content = response.getBody();

        assertThat(response.getStatus()).isEqualTo(200);
        assertThat(content).contains("Stacee Tremblay");
        assertThat(content).contains("Solomon Bayer");
        assertThat(content).contains("Serina Schaden");
        assertThat(content).doesNotContain("Bobbi Wehner");
    }

    @Test
    void testListUsers2() throws Exception {
        HttpResponse<String> response = Unirest.get(baseUrl + "/users?term=WI").asString();
        String content = response.getBody();

        assertThat(response.getStatus()).isEqualTo(200);
        assertThat(content).contains("Will Casper");
        assertThat(content).contains("Willis Jast");
        assertThat(content).doesNotContain("Wendell Legros");
    }
}
