package learning.springboot.web.app;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class SpringbootLearningAppApplication implements CommandLineRunner {

    private static final Logger logger = LoggerFactory.getLogger(SpringbootLearningAppApplication.class);


    public static void main(String[] args) {
        SpringApplication.run(SpringbootLearningAppApplication.class, args);
    }


    @Override
    public void run(String... args) {
        logger.info("Spring Application run");
    }
}
