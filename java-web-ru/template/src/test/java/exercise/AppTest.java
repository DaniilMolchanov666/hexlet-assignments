package exercise;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;
import io.javalin.testtools.JavalinTest;
import io.javalin.Javalin;

class AppTest {

    private static Javalin app;

    @BeforeEach
    public void setUp() {
        app = App.getApp();
    }

    @Test
    void testRootPage() throws Exception {
        JavalinTest.test(app, (server, client) -> {
            assertThat(client.get("/").code()).isEqualTo(200);
        });
    }

    @Test
    void testListUsers() throws Exception {

        JavalinTest.test(app, (server, client) -> {
            var response = client.get("/users");
            assertThat(response.code()).isEqualTo(200);
            assertThat(response.body().string())
                .contains("Bobbi", "Wehner")
                .contains("Will", "Casper");
        });
    }

    @Test
    void testShowUser1() throws Exception {
        JavalinTest.test(app, (server, client) -> {
            var response = client.get("/users/1");
            assertThat(response.code()).isEqualTo(200);
            assertThat(response.body().string())
                .contains("Pearl", "Schultz")
                .doesNotContain("Bobbi", "Wehner");
        });
    }

    @Test
    void testShowUser2() throws Exception {
        JavalinTest.test(app, (server, client) -> {
            var response = client.get("/users/5");
            assertThat(response.code()).isEqualTo(200);
            assertThat(response.body().string())
                .contains("Minerva", "Altenwerth")
                .doesNotContain("Ilse", "Roob");
        });
    }
}
