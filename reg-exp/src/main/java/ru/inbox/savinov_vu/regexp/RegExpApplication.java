package ru.inbox.savinov_vu.regexp;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;

@SpringBootApplication
@EnableAutoConfiguration(exclude = {
        SecurityAutoConfiguration.class
})
//public class RegExpApplication implements CommandLineRunner {
public class RegExpApplication {

    public static void main(String[] args) {
        SpringApplication.run(RegExpApplication.class, args);
    }

 /*   @Override
    public void run(String... args) throws Exception {
        System.out.println("sldkfjsldfj");
    }*/
}
