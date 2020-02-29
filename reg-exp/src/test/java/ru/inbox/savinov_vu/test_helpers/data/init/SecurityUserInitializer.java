package ru.inbox.savinov_vu.test_helpers.data.init;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.inbox.savinov_vu.app.users.model.User;
import ru.inbox.savinov_vu.core.security.SecurityService;
import ru.inbox.savinov_vu.core.security.jwt.dto.SignUpDto;
import ru.inbox.savinov_vu.core.security.jwt.model.SecurityUser;

import javax.annotation.Resource;



@Service
@RequiredArgsConstructor
public class SecurityUserInitializer {

  @Resource
  private final UserInitializer userInitializer;

  @Resource
  private final SecurityService securityService;

  public SecurityUser initOne() {
    deleteAll();
    User user = userInitializer.initOne();
    SecurityUser securityUser = SecurityUser.of(user);
    return securityUser;
  }

  public SecurityUser initOneWithSignUpDto(SignUpDto signUpDto) {
    deleteAll();
    User user = securityService.signUp(signUpDto);
    SecurityUser securityUser = SecurityUser.of(user);
    return securityUser;
  }


  public void deleteAll() {
    userInitializer.deleteAll();
  }
}
