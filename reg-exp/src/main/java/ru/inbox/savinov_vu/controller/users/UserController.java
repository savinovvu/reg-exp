package ru.inbox.savinov_vu.controller.users;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.MediaType;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;
import ru.inbox.savinov_vu.config.security.JwtAuthenticationRequest;
import ru.inbox.savinov_vu.config.security.JwtAuthenticationResponse;
import ru.inbox.savinov_vu.config.security.JwtTokenUtil;
import ru.inbox.savinov_vu.interfaces.CRUD.CRUDController;
import ru.inbox.savinov_vu.interfaces.OperationResulter;
import ru.inbox.savinov_vu.model.users.Authority;
import ru.inbox.savinov_vu.model.users.AuthorityName;
import ru.inbox.savinov_vu.model.users.User;
import ru.inbox.savinov_vu.service.users.UserService;

import java.util.List;
import java.util.Objects;



@RestController
@RequestMapping(value = "/users/user", consumes = MediaType.ALL_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
public class UserController implements CRUDController<User> {

    @Value("${jwt.header}")
    private String tokenHeader;

    @Autowired
    UserService userService;

    @Autowired
    private JwtTokenUtil jwtTokenUtil;

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    PasswordEncoder passwordEncoder;


    @Override
    public OperationResulter<String> add(User user) {
        return userService.add(user);
    }


    @Override
    public List<User> getAll() {
        return userService.getAll();
    }


    @Override
    public User getById(@PathVariable("id") Integer id) {
        return userService.getById(id);
    }


    @Override
    public boolean delete(@PathVariable("id") Integer id) {
        userService.delete(id);
        return true;
    }


    @Override
    public User update(User user) {
        return userService.update(user);
    }


    @PostMapping("signin")
    @CrossOrigin
    public JwtAuthenticationResponse signin(@RequestBody JwtAuthenticationRequest authenticationRequest) {
        String login = authenticationRequest.login;
        String password = authenticationRequest.password;
        authenticate(login, password);
        UserDetails userDetails = userService.loadUserByUsername(login);
        return new JwtAuthenticationResponse(jwtTokenUtil.generateToken(userDetails));
    }


    private void authenticate(String username, String password) {
        Objects.requireNonNull(username);
        Objects.requireNonNull(password);
        authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(username, password));
    }


    //todo: implement enabled: true with mailService.
    @PostMapping("signup")
    @CrossOrigin
    public JwtAuthenticationResponse signup(@RequestBody User user) {
        user.setEnabled(true);
        String encodePassword = passwordEncoder.encode(user.getPassword());
        user.setPassword(encodePassword);
        user.setAuthorities(List.of(new Authority(1, AuthorityName.User)));
        User savedUser = userService.signup(user);
        return new JwtAuthenticationResponse(jwtTokenUtil.generateToken(savedUser));
    }


}
