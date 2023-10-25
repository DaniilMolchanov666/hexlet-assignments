package exercise;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import kong.unirest.core.Unirest;

class AppTest {
    static String baseUrl;

    @BeforeAll
    static void setup() {
        baseUrl = System.getProperty("gretty.httpBaseURI");
    }

    @Test
    void testMainPage() {
        var response = Unirest.get(baseUrl).asString();
        assertThat(response.getStatus()).isEqualTo(200);
        assertThat(response.getBody()).contains("Admin");
    }

    @Test
    void testHelloPage() {
        var userName = "user1";
        var response = Unirest.get(baseUrl + "/hello?name=" + userName).asString();
        assertThat(response.getStatus()).isEqualTo(200);
        assertThat(response.getBody()).contains("Hello, " + userName + "!");
    }
}
