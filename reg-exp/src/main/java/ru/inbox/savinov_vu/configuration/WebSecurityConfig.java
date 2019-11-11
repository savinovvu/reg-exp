package ru.inbox.savinov_vu.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import ru.inbox.savinov_vu.core.security.CustomFailureHandler;
import ru.inbox.savinov_vu.core.security.SecurityService;

import javax.annotation.Resource;
import java.util.regex.Pattern;



@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(securedEnabled = false, prePostEnabled = true, jsr250Enabled = true)
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

  private static final Pattern DOMAIN_PATTERN = Pattern.compile("^.+?\\.([A-Za-z0-9\\-]+\\.[a-z]+)[:0-9]*$");


  @Resource
  private SecurityService userDetailsService;


  @Bean
  @Override
  public AuthenticationManager authenticationManagerBean() throws Exception {
    return super.authenticationManagerBean();
  }


  @Bean
  public CustomFailureHandler unauthorizedEntryPoint() {
    return new CustomFailureHandler();
  }


  @Override
  protected void configure(AuthenticationManagerBuilder auth) throws Exception {
    auth.inMemoryAuthentication()
      .and()
      .userDetailsService(userDetailsService).passwordEncoder(passwordEncoder());
  }


  @Override
  protected void configure(HttpSecurity http) throws Exception {
    String[] publicPaths = new String[]{
      "/page/sign-up", "/page/users/user",
      "/css/**", "/icons/**", "/images/**", "/js/**", "/layer/**",
      "/fonts/**", "/v1/tasks/regexplevel"
    };

    http
      .headers().frameOptions().disable()
      .and()

      .csrf().disable().authorizeRequests()


      .and()
      .authorizeRequests()
      .antMatchers(publicPaths).permitAll()
      .anyRequest().authenticated()

      .and()
      .formLogin()
      .loginPage("/login")
      .loginProcessingUrl("/v1/sign-in")
      .failureHandler(unauthorizedEntryPoint())
      .permitAll()
      .defaultSuccessUrl("/main")
      .usernameParameter("login")
      .passwordParameter("password")

      .and()
      .anonymous()
      .principal("System")


      .and()
      .logout()
      .logoutUrl("/v1/logout")
      .deleteCookies("remember-me")
      .permitAll()

      .and()
      .exceptionHandling()
      .authenticationEntryPoint(unauthorizedEntryPoint())

      .and()
      .rememberMe()
      .rememberMeCookieName("remember-me")
      .userDetailsService(userDetailsService)
      .key("userSecurityKey")
      .tokenValiditySeconds(864000);
  }


  @Bean
  public BCryptPasswordEncoder passwordEncoder() {
    return new BCryptPasswordEncoder();
  }


}
