package ru.inbox.savinov_vu.config;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import ru.inbox.savinov_vu.utils.WebTestHelper;



@TestConfiguration
@EnableWebSecurity
public class ControllerTestCommonConfiguration extends WebSecurityConfigurerAdapter {

  @Bean
  public ObjectMapper objectMapper() {
    ObjectMapper objectMapper = new ObjectMapper();
    return objectMapper;
  }


  @Bean
  public WebTestHelper requestPerformer() {
    WebTestHelper requestPerformer = new WebTestHelper();
    return requestPerformer;
  }


  @Override
  public void configure(WebSecurity web) {
    web.debug(true);
  }


  @Override
  protected void configure(HttpSecurity http) throws Exception {
    http.csrf().disable().authorizeRequests().anyRequest().permitAll();
  }

}
