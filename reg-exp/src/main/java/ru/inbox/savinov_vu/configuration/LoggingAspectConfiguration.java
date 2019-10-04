package ru.inbox.savinov_vu.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.core.env.Environment;
import ru.inbox.savinov_vu.core.aop.logging.LoggingAspect;



@Configuration
@EnableAspectJAutoProxy
public class LoggingAspectConfiguration {

  @Bean
  public LoggingAspect loggingAspect(Environment env) {
    return new LoggingAspect(env);
  }
}
