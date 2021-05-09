package ru.inbox.savinov_vu.configuration;

import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import ru.inbox.savinov_vu.core.security.handler.CustomFailureHandler;
import ru.inbox.savinov_vu.core.security.jwt.config.JwtAuthorizationTokenFilter;
import ru.inbox.savinov_vu.core.security.jwt.config.JwtHelper;
import ru.inbox.savinov_vu.core.security.service.SecurityService;

import javax.annotation.Resource;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;


@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(securedEnabled = true, prePostEnabled = true, jsr250Enabled = true)
@RequiredArgsConstructor
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

  @Resource
  private JwtHelper jwtHelper;

  @Resource
  private SecurityService securityService;

  public static final Set<String> PUBLIC_PATHS = new HashSet<>(Arrays.asList(
    "/v1/sign-up",
    "/v1/sign-in",
    "/v1/sign-in/guest",
    "/v1/log-out"));


  @Override
  protected void configure(AuthenticationManagerBuilder auth) throws Exception {
    auth
      .userDetailsService(securityService)
      .passwordEncoder(passwordEncoder());
  }


  @Override
  protected void configure(HttpSecurity http) throws Exception {

    http
      .csrf().disable()
      .sessionManagement()
      .sessionCreationPolicy(SessionCreationPolicy.STATELESS)
      .sessionFixation().none()

      .and()

      .authorizeRequests()
      .antMatchers(PUBLIC_PATHS.toArray(String[]::new)).permitAll()
      .anyRequest().authenticated()

      .and()
      .formLogin()
      .loginPage("/login")
      .loginProcessingUrl("/v1/sign-in")
      .failureHandler(unauthorizedEntryPoint())
      .permitAll()


      .and()
      .logout()
      .logoutUrl("/v1/logout")
      .permitAll()

      .and()
      .exceptionHandling()
      .authenticationEntryPoint(unauthorizedEntryPoint());


    http.addFilterBefore(jwtAuthenticationFilter(), UsernamePasswordAuthenticationFilter.class);
  }


  @Override
  public void configure(WebSecurity web) throws Exception {
    // AuthenticationTokenFilter will ignore the below paths
    web.ignoring()
      .antMatchers(
        HttpMethod.POST,
        "/v1/sign-in",
        "/v1/sign-up",
        "/v1/sign-in/guest",
        "/v1/log-out"
      );
  }


  @Bean
  public BCryptPasswordEncoder passwordEncoder() {
    return new BCryptPasswordEncoder();
  }


  @Bean
  public JwtAuthorizationTokenFilter jwtAuthenticationFilter() {
    return new JwtAuthorizationTokenFilter(userDetailsService(), jwtHelper, securityService);
  }


  @Bean
  public CustomFailureHandler unauthorizedEntryPoint() {
    return new CustomFailureHandler();
  }


  @Bean
  @Override
  public AuthenticationManager authenticationManagerBean() throws Exception {
    return super.authenticationManagerBean();
  }


}
