package exercise;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import kong.unirest.HttpResponse;
import kong.unirest.Unirest;
import io.javalin.Javalin;
import exercise.repository.PostRepository;
import exercise.model.Post;

class AppTest {

    private static Javalin app;
    private static String baseUrl;
    private static Post existingPost;

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
        PostRepository.clear();
        existingPost = new Post("test name", "test body 1");
        PostRepository.save(existingPost);
    }

    @Test
    void testRootPage() throws Exception {
        HttpResponse<String> response = Unirest.get(baseUrl + "/").asString();
        assertThat(response.getStatus()).isEqualTo(200);
    }

    @Test
    void testListPosts() throws Exception {
        HttpResponse<String> response = Unirest.get(baseUrl + "/posts").asString();
        assertThat(response.getStatus()).isEqualTo(200);
    }

    @Test
    void testBuildPost() throws Exception {
        HttpResponse<String> response = Unirest.get(baseUrl + "/posts/build").asString();
        assertThat(response.getStatus()).isEqualTo(200);
    }

    @Test
    void testCreatePost() throws Exception {
        HttpResponse responsePost = Unirest
            .post(baseUrl + "/posts")
            .field("name", "test name 2")
            .field("body", "test body 2")
            .asEmpty();

        assertThat(responsePost.getStatus()).isEqualTo(302);
        assertThat(responsePost.getHeaders().getFirst("Location")).isEqualTo("/posts");

        HttpResponse<String> response = Unirest
            .get(baseUrl + "/posts")
            .asString();
        String body = response.getBody();
        assertThat(body).contains("test name 2");
        assertThat(body).contains("test body 2");

        var post = PostRepository.findByName("test name 2");
        assertThat(post).isNotNull();
    }

    @Test
    void testShowPost() throws Exception {
        HttpResponse<String> response = Unirest
            .get(baseUrl + "/posts/" + existingPost.getId())
            .asString();
        String body = response.getBody();
        assertThat(body).contains(existingPost.getName());
        assertThat(body).contains(existingPost.getBody());
        assertThat(body).contains("posts/" + existingPost.getId() + "/edit");
    }

    @Test
    void testCreatePostNegative() throws Exception {
        HttpResponse responsePost = Unirest
            .post(baseUrl + "/posts")
            .field("name", "test name")
            .field("body", "test")
            .asEmpty();

        assertThat(responsePost.getStatus()).isEqualTo(422);
    }

    @Test
    void testEditPost() throws Exception {
        HttpResponse<String> response = Unirest
            .get(baseUrl + "/posts/" + existingPost.getId() + "/edit").asString();
        assertThat(response.getStatus()).isEqualTo(200);
    }

    @Test
    void testUpdatePost() throws Exception {
        HttpResponse<String> responsePost = Unirest
            .post(baseUrl + "/posts/" + existingPost.getId())
            .field("name", "new name")
            .field("body", "test content")
            .asString();

        assertThat(responsePost.getStatus()).isEqualTo(302);
        assertThat(PostRepository.existsByName("new name")).isEqualTo(true);
    }

    @Test
    void testUpdatePostNegative() throws Exception {
        HttpResponse<String> responsePost = Unirest
            .post(baseUrl + "/posts/" + existingPost.getId())
            .field("name", "q")
            .field("body", "test content")
            .asString();

        assertThat(responsePost.getStatus()).isEqualTo(422);

        String body = responsePost.getBody();
        assertThat(body).contains("q");
        assertThat(body).contains("test content");

        assertThat(PostRepository.existsByName("q")).isEqualTo(false);
    }

    @Test
    void testPostNotFound() throws Exception {
        HttpResponse<String> response = Unirest
            .get(baseUrl + "/posts/" + existingPost.getId() + 1 + "/edit").asString();
        assertThat(response.getStatus()).isEqualTo(500);
    }
}
