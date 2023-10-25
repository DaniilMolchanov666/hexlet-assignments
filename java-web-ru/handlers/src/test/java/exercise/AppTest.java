package exercise;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;
import kong.unirest.HttpResponse;
import kong.unirest.Unirest;
import io.javalin.Javalin;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.core.type.TypeReference;
import java.util.List;


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
    void testDomains() throws Exception {
        ObjectMapper mapper = new ObjectMapper();
        HttpResponse<String> response = Unirest.get(baseUrl + "/domains").asString();
        String content = response.getBody();
        List<String> actual = mapper.readValue(content, new TypeReference<List<String>>() { });
        assertThat(response.getStatus()).isEqualTo(200);
        assertThat(actual).hasSize(10);


    }

    @Test
    void testPhones() throws Exception {
        ObjectMapper mapper = new ObjectMapper();
        HttpResponse<String> response = Unirest.get(baseUrl + "/phones").asString();
        String content = response.getBody();
        List<String> actual = mapper.readValue(content, new TypeReference<List<String>>() { });
        assertThat(response.getStatus()).isEqualTo(200);
        assertThat(actual).hasSize(10);
    }
}
