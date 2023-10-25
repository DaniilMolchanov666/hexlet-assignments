package exercise;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.Test;
import kong.unirest.HttpResponse;
import kong.unirest.Unirest;
import io.javalin.Javalin;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.core.type.TypeReference;
import java.util.List;
import java.util.Map;


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
    void testUsersWithDefaultParams() throws Exception {
        ObjectMapper mapper = new ObjectMapper();
        List<Map<String, String>> expected = List.of(
            Map.of("firstName", "Bobbi", "lastName", "Wehner", "id", "6"),
            Map.of("firstName", "Robbi", "lastName", "Wintheiser", "id", "23"),
            Map.of("firstName", "Joey", "lastName", "Braun", "id", "25"),
            Map.of("firstName", "Leticia", "lastName", "Wehner", "id", "1"),
            Map.of("firstName", "Nathanael", "lastName", "Kirlin", "id", "14")
        );

        HttpResponse<String> response = Unirest.get(baseUrl + "/users").asString();
        String content = response.getBody();
        List<Map<String, String>> actual = mapper.readValue(
            content, new TypeReference<List<Map<String, String>>>() { }
        );
        assertThat(response.getStatus()).isEqualTo(200);
        assertThat(actual).isEqualTo(expected);
    }

    @Test
    void testUsers1() throws Exception {
        ObjectMapper mapper = new ObjectMapper();
        List<Map<String, String>> expected = List.of(
            Map.of("firstName", "Joey", "lastName", "Braun", "id", "25")
        );

        HttpResponse<String> response = Unirest.get(baseUrl + "/users?page=3&per=1").asString();
        String content = response.getBody();
        List<Map<String, String>> actual = mapper.readValue(
            content, new TypeReference<List<Map<String, String>>>() { }
        );
        assertThat(response.getStatus()).isEqualTo(200);
        assertThat(actual).isEqualTo(expected);
    }

    @Test
    void testUsers2() throws Exception {
        ObjectMapper mapper = new ObjectMapper();
        List<Map<String, String>> expected = List.of(
            Map.of("firstName", "Marlon", "lastName", "Kozey", "id", "16"),
            Map.of("firstName", "Cleo", "lastName", "Cole", "id", "11"),
            Map.of("firstName", "Solomon", "lastName", "Bayer", "id", "10")
        );

        HttpResponse<String> response = Unirest.get(baseUrl + "/users?page=5&per=3").asString();
        String content = response.getBody();
        List<Map<String, String>> actual = mapper.readValue(
            content, new TypeReference<List<Map<String, String>>>() { }
        );
        assertThat(response.getStatus()).isEqualTo(200);
        assertThat(actual).isEqualTo(expected);
    }
}
