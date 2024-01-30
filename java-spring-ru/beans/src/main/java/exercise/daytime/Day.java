package exercise.daytime;
import jakarta.annotation.PostConstruct;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.time.LocalDateTime;

public class Day implements Daytime {
    private String name = "day";

    public String getName() {
        return name;
    }

    @PostConstruct
    public void getDate() {
        System.out.println("It is " + name + " now! Welcome to Spring!");
    }
}
