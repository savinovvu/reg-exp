package ru.inbox.savinov_vu.security.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.inbox.savinov_vu.security.config.JwtAuthenticationRequest;
import ru.inbox.savinov_vu.security.config.JwtAuthenticationResponse;
import ru.inbox.savinov_vu.security.config.JwtTokenUtil;
import ru.inbox.savinov_vu.security.model.Authority;
import ru.inbox.savinov_vu.security.model.SecurityUser;
import ru.inbox.savinov_vu.security.service.SecurityService;

import java.util.Objects;
import java.util.Set;


@RestController
@RequestMapping(value = "/users/user", consumes = MediaType.ALL_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
public class SecurityController {

    @Autowired
    private JwtTokenUtil jwtTokenUtil;

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    PasswordEncoder passwordEncoder;

    @Autowired
    SecurityService securityService;


    @PostMapping("signin")
    @CrossOrigin
    public JwtAuthenticationResponse signin(@RequestBody JwtAuthenticationRequest authenticationRequest) {
        String login = authenticationRequest.login;
        String password = authenticationRequest.password;
        authenticate(login, password);
        SecurityUser user = (SecurityUser) securityService.loadUserByUsername(login);

        return getJwtAuthenticationResponse(user);
    }


    private void authenticate(String username, String password) {
        Objects.requireNonNull(username);
        Objects.requireNonNull(password);
        authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(username, password));
    }


    //todo: implement enabled: true with mailService.
    @PostMapping("signup")
    @CrossOrigin
    public JwtAuthenticationResponse signup(@RequestBody SecurityUser user) {
        user.setEnabled(true);
        String encodePassword = passwordEncoder.encode(user.getPassword());
        user.setPassword(encodePassword);
        user.setAuthorities(Set.of(Authority.User));
        SecurityUser savedUser = securityService.signup(user);
        return getJwtAuthenticationResponse(savedUser);
    }




    private JwtAuthenticationResponse getJwtAuthenticationResponse(SecurityUser user) {
        String token = jwtTokenUtil.generateToken(user);
        String name = user.getName();
        Integer id = user.getId();
        Set<Authority> authorities = user.getAuthorities();
        return new JwtAuthenticationResponse(token, id, name, authorities);
    }
}
