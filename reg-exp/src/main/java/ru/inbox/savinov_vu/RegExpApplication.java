package ru.inbox.savinov_vu;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import ru.inbox.savinov_vu.core.security.jwt.config.JwtParams;



@SpringBootApplication
@EnableJpaAuditing
@EnableConfigurationProperties({ JwtParams.class})
public class RegExpApplication {

  public static void main(String[] args) {
    SpringApplication.run(RegExpApplication.class, args);
  }

}

