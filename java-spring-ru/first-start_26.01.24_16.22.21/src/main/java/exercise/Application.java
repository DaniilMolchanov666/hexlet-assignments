package exercise;

import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@SpringBootApplication
@RestController
public class Application {
    public static void main(String[] args) {
//        SpringApplication.run(Application.class, args);
        AnnotationConfigApplicationContext c = new AnnotationConfigApplicationContext(Config.class);
        Cat cat = (Cat) c.getBean("cat");
        cat.say();

        System.out.println(cat.catGet);
        System.out.println(cat.id);



        c.close();
    }

    @GetMapping("/about")
    String home() {
        return "Welcome to Hexlet!";
    }
}
