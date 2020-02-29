package ru.inbox.savinov_vu.core.security;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.security.core.userdetails.UserDetails;
import ru.inbox.savinov_vu.app.users.model.User;
import ru.inbox.savinov_vu.config.AbstractSpringBootTest;
import ru.inbox.savinov_vu.core.exception.AuthenticationException;
import ru.inbox.savinov_vu.core.security.jwt.dto.SignUpDto;
import ru.inbox.savinov_vu.core.security.jwt.model.SecurityUser;
import ru.inbox.savinov_vu.test_helpers.data.factories.jwt.SignUpDtoFactory;
import ru.inbox.savinov_vu.test_helpers.data.init.SecurityUserInitializer;

import javax.annotation.Resource;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;



class SecurityServiceTest extends AbstractSpringBootTest {

  @Resource
  private SecurityUserInitializer securityUserInitializer;

  @Resource
  private SecurityService securityService;


  private SignUpDto signUpDto;


  @BeforeEach
  public void init() {
    signUpDto = SignUpDtoFactory.of();
    securityUserInitializer.initOneWithSignUpDto(signUpDto);
  }


  @Test
  void loadUserByUsername() {

    UserDetails userDetails = securityService.loadUserByUsername(signUpDto.getLogin());
    assertEquals(signUpDto.getLogin(), userDetails.getUsername());
  }


  @Test
  void authenticate_invalid() {
    assertThrows(AuthenticationException.class, () -> {
      securityService.authenticate("sldf123", "sdf34");
    });
  }


  @Test
  void authenticate_valid() {
    SecurityUser authenticate = securityService.authenticate(signUpDto.getLogin(), signUpDto.getPassword());
    assertEquals(signUpDto.getLogin(), authenticate.getLogin());
  }


  @Test
  void signUp() {
    SignUpDto signUpDto = SignUpDtoFactory.of("1");
    User user = securityService.signUp(signUpDto);
    assertEquals(user.getLogin(), signUpDto.getLogin());
  }
}
