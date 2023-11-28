package goodMorningBot;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.scheduling.annotation.EnableScheduling;

import java.util.TimeZone;

@SpringBootApplication
@EnableScheduling
public class GoodMorningBotApp {

    public static void main(String[] args) {
        SpringApplication.run(GoodMorningBotApp.class, args);
    }
}
