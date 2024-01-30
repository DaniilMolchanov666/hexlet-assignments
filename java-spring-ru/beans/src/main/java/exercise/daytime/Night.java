package exercise.daytime;
import jakarta.annotation.PostConstruct;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

public class Night implements Daytime {
    private String name = "night";

    public String getName() {
        return name;
    }

    @PostConstruct
    public void getDate() {
        System.out.println(this.name);
        System.out.println(LocalDateTime.now().getHour());
    }
}
