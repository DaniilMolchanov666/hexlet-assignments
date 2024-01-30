package exercise;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.time.LocalDateTime;
import java.time.chrono.ChronoLocalDateTime;
import java.time.chrono.Chronology;

import exercise.daytime.Daytime;
import exercise.daytime.Day;
import exercise.daytime.Night;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Scope;

@SpringBootApplication
public class Application {

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }

    @Bean
    @Scope(scopeName = "prototype")
    public Daytime date() {
        int hour = LocalDateTime.now().getHour();
        if (hour <=  22 && hour >= 6) {
            return new Day();
        } else {
            return new Night();
        }
    }
}
