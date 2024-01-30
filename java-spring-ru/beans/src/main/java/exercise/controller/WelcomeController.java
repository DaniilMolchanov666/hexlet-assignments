package exercise.controller;

import exercise.daytime.Daytime;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.GetMapping;

@RestController
@RequestMapping("/welcome")
public class WelcomeController {

    @Autowired
    public Daytime daytime;

    @GetMapping()
    public Daytime getDateTime() {
        return daytime;
    }
}
