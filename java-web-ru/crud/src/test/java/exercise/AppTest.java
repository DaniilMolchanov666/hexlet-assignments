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
        assertThat(response.getStatus()).isEqualTo(200);
    }

    @Test
    void testListArticles1() throws Exception {
        HttpResponse<String> response = Unirest.get(baseUrl + "/posts").asString();
        String body = response.getBody();

        assertThat(response.getStatus()).isEqualTo(200);
        assertThat(body).contains("Sleep the Brave");
        assertThat(body).contains("This Side of Paradise");
        assertThat(body).doesNotContain("Little Hands Clapping");
        assertThat(body).contains("?page=2");
    }

    @Test
    void testListArticles2() throws Exception {
        HttpResponse<String> response = Unirest.get(baseUrl + "/posts?page=2").asString();
        String body = response.getBody();

        assertThat(response.getStatus()).isEqualTo(200);
        assertThat(body).contains("Little Hands Clapping");
        assertThat(body).contains("Moab Is My Washpot");
        assertThat(body).doesNotContain("This Side of Paradise");
        assertThat(body).doesNotContain("To Say Nothing of the Dog");
        assertThat(body).contains("?page=1");
        assertThat(body).contains("?page=3");
    }

    @Test
    void testShowArticle() throws Exception {
        HttpResponse<String> response = Unirest.get(baseUrl + "/posts/2").asString();
        String body = response.getBody();

        assertThat(response.getStatus()).isEqualTo(200);
        assertThat(body).contains("No Country for Old Men");
        assertThat(body).contains("Ut sint autem.");
    }

    @Test
    void testPostNotFound() throws Exception {
        HttpResponse<String> response = Unirest.get(baseUrl + "/posts/999").asString();
        assertThat(response.getStatus()).isEqualTo(500);
    }

}
