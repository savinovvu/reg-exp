package ru.inbox.savinov_vu;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;



@SpringBootApplication
@EnableAutoConfiguration/*(exclude = {
        SecurityAutoConfiguration.class
})*/
public class RegExpApplication {

    public static void main(String[] args) {
        SpringApplication.run(RegExpApplication.class, args);
    }

}
