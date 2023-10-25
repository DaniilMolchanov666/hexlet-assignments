package exercise;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;
import io.javalin.Javalin;
import io.javalin.testtools.JavalinTest;

class AppTest {

    private static Javalin app;

    @BeforeEach
    public void setUp() {
        app = App.getApp();
    }

    @Test
    void testMainPage() throws Exception {

        JavalinTest.test(app, (server, client) -> {
            var response = client.get("/");
            assertThat(response.code()).isEqualTo(200);
            assertThat(response.body().string()).contains("/", "/users");
        });
    }

    @Test
    void testUsersPage() throws Exception {

        JavalinTest.test(app, (server, client) -> {
            var response = client.get("/users");
            assertThat(response.code()).isEqualTo(200);
            assertThat(response.body().string())
                .contains("Bobbi Wehner", "Will Casper", "Darryl Ward")
                .contains("/", "/users");
        });
    }

    @Test
    void testUserPage1() throws Exception {

        JavalinTest.test(app, (server, client) -> {
            var response = client.get("/users/1");
            assertThat(response.code()).isEqualTo(200);
            assertThat(response.body().string())
                .contains("/", "/users")
                .contains("Pearl Schultz")
                .doesNotContain("Bobbi Wehner");
        });
    }

    @Test
    void testUserPage2() throws Exception {

        JavalinTest.test(app, (server, client) -> {
            var response = client.get("/users/5");
            assertThat(response.code()).isEqualTo(200);
            assertThat(response.body().string())
                .contains("/", "/users")
                .contains("Minerva Altenwerth")
                .doesNotContain("Ilse Roob");
        });
    }
}
