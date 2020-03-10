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
import ru.inbox.savinov_vu.core.security.jwt.config.JwtParams;
import ru.inbox.savinov_vu.core.security.service.SecurityService;

import javax.annotation.Resource;



@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(securedEnabled = true, prePostEnabled = true, jsr250Enabled = true)
@RequiredArgsConstructor
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

  @Resource
  private JwtHelper jwtHelper;

  @Resource
  private SecurityService securityService;

  @Resource
  private JwtParams jwtParams;


  @Override
  protected void configure(AuthenticationManagerBuilder auth) throws Exception {
    auth
      .userDetailsService(securityService)
      .passwordEncoder(passwordEncoder());
  }


  @Override
  protected void configure(HttpSecurity http) throws Exception {
    String[] publicPaths = new String[]{
      "/v1/sign-up", "/page/users/user", "/v1/sign-in",
      "/css/**", "/icons/**", "/images/**", "/js/**", "/layer/**",
      "/fonts/**", "/v1/tasks/regexplevel", "/monitoring/**", "/v1/sign-in/guest",
      "/v1/log-out"
    };

    http
      .csrf().disable()
      .sessionManagement()
      .sessionCreationPolicy(SessionCreationPolicy.STATELESS)
      .sessionFixation().none()

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
      .userDetailsService(securityService)
      .key("userSecurityKey")
      .tokenValiditySeconds(jwtParams.getExpiration());

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
      )

      // allow anonymous resource requests
      .and()
      .ignoring()
      .antMatchers(
        HttpMethod.GET,
        "/",
        "/*.html",
        "/favicon.ico",
        "/**/*.html",
        "/**/*.css",
        "/**/*.js"
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
