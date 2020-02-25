package ru.inbox.savinov_vu.core.security.jwt.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.User;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import ru.inbox.savinov_vu.core.exception.AuthenticationException;
import ru.inbox.savinov_vu.core.security.SecurityService;
import ru.inbox.savinov_vu.core.security.jwt.config.JwtHelper;
import ru.inbox.savinov_vu.core.security.jwt.dto.LoginDto;
import ru.inbox.savinov_vu.core.security.jwt.dto.TokenDto;

import javax.annotation.Resource;
import javax.validation.Valid;



@RestController
@RequiredArgsConstructor
public class LoginController {

  @Resource
  private SecurityService securityService;

  @Resource
  private JwtHelper jwtHelper;


  @PostMapping("/v1/sign-in")
  public ResponseEntity login(@Valid @RequestBody LoginDto loginDTO) {

    User securityUser = securityService.authenticate(loginDTO.getLogin(), loginDTO.getPassword())
      .orElseThrow(
        () -> new AuthenticationException("Invalid login/password for user " + loginDTO.getLogin()));

    String token = jwtHelper.generateToken(securityUser);


    return ResponseEntity.ok(new TokenDto(token));
  }
}
