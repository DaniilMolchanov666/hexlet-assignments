package exercise;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController

public class WelcomeController {

    @GetMapping("/users")

    public String welcome(@RequestParam(value = "daniil")String name) {
        return name;
    }
}