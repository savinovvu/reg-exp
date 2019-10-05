package ru.inbox.savinov_vu;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;



@SpringBootApplication
@EnableJpaAuditing
public class RegExpApplication {

  public static void main(String[] args) {
    SpringApplication.run(RegExpApplication.class, args);
  }

}

