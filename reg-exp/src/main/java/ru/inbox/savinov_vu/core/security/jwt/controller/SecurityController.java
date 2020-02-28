package ru.inbox.savinov_vu.core.security.jwt.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import ru.inbox.savinov_vu.core.exception.AuthenticationException;
import ru.inbox.savinov_vu.core.security.SecurityService;
import ru.inbox.savinov_vu.core.security.jwt.config.JwtAuthenticationResponse;
import ru.inbox.savinov_vu.core.security.jwt.config.JwtHelper;
import ru.inbox.savinov_vu.core.security.jwt.dto.LoginDto;
import ru.inbox.savinov_vu.core.security.jwt.dto.SignUpDto;
import ru.inbox.savinov_vu.core.security.jwt.model.SecurityUser;

import javax.annotation.Resource;
import javax.validation.Valid;



@RestController
@RequiredArgsConstructor
public class SecurityController {

  @Resource
  private SecurityService securityService;

  @Resource
  private JwtHelper jwtHelper;


  @PostMapping("/v1/sign-in")
  public ResponseEntity login(@Valid @RequestBody LoginDto loginDTO) {

    SecurityUser securityUser = securityService.authenticate(loginDTO.getLogin(), loginDTO.getPassword())
      .orElseThrow(
        () -> new AuthenticationException("Invalid login/password for user " + loginDTO.getLogin()));

    String token = jwtHelper.generateToken(securityUser.getLogin());

    return ResponseEntity.ok(JwtAuthenticationResponse.of(securityUser, token));
  }


  @PostMapping("/v1/sign-up")
  public ResponseEntity signUp(@Valid @RequestBody SignUpDto signUpDto) {
    if (!signUpDto.getPassword().equals(signUpDto.getRepeatPassword())) {
      return new ResponseEntity(HttpStatus.BAD_REQUEST);
    }
    securityService.signUp(signUpDto);
    return ResponseEntity.ok().build();
  }
}
