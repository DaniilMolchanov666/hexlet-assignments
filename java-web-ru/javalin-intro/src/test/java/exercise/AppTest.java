package exercise;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;
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
    void testRoot() {
        HttpResponse<String> response = Unirest.get(baseUrl + "/welcome").asString();
        String content = response.getBody();
        assertEquals(200, response.getStatus());
        assertEquals("Welcome to Hexlet!", content);
    }
}
