package ru.inbox.savinov_vu.configuration;

import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import ru.inbox.savinov_vu.core.security.CustomFailureHandler;
import ru.inbox.savinov_vu.core.security.SecurityService;
import ru.inbox.savinov_vu.core.security.jwt.config.JwtAuthorizationTokenFilter;
import ru.inbox.savinov_vu.core.security.jwt.config.JwtParams;
import ru.inbox.savinov_vu.core.security.jwt.config.JwtHelper;

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
      "/page/sign-up", "/page/users/user",
      "/css/**", "/icons/**", "/images/**", "/js/**", "/layer/**",
      "/fonts/**", "/v1/tasks/regexplevel", "/monitoring/**"
    };

    http
      .csrf().disable()
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
      .userDetailsService(securityService)
      .key("userSecurityKey")
      .tokenValiditySeconds(jwtParams.getExpiration());

    http.addFilterBefore(jwtAuthenticationFilter(), UsernamePasswordAuthenticationFilter.class);
  }


  @Bean
  public BCryptPasswordEncoder passwordEncoder() {
    return new BCryptPasswordEncoder();
  }


  @Bean
  public JwtAuthorizationTokenFilter jwtAuthenticationFilter() {
    return new JwtAuthorizationTokenFilter(userDetailsService(), jwtHelper, jwtParams.getHeader());
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
