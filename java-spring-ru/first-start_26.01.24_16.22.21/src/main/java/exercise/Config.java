package exercise;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;

@Configuration
@ComponentScan("exercise")
public class Config {

    @Bean
    @Scope("prototype")
    public Cat cat() {
        return new Cat();
    }

    @Bean
    @Scope("singleton")
    public CatGet catGet() {
        return new CatGet(cat());
    }
}
