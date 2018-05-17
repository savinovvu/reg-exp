package ru.inbox.savinov_vu.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import ru.inbox.savinov_vu.service.users.UserService;



@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    UserService userService;

    public SecurityConfig() {
        super(true);
    }


    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.securityContext().and().exceptionHandling()
        .and().servletApi().and().httpBasic()
        .and().formLogin().disable()
        .logout().and()
        .anonymous().principal("guest").authorities("Guest")
        .and().rememberMe();
    }


    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userService);
    }



}
