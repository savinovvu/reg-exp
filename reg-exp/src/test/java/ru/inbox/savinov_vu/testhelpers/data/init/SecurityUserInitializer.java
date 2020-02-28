package ru.inbox.savinov_vu.testhelpers.data.init;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.inbox.savinov_vu.app.users.model.User;
import ru.inbox.savinov_vu.core.security.jwt.model.SecurityUser;

import javax.annotation.Resource;



@Service
@RequiredArgsConstructor
public class SecurityUserInitializer {

  @Resource
  private final UserInitializer userInitializer;


  public SecurityUser initOne() {
    User user = userInitializer.initOne();
    SecurityUser securityUser = SecurityUser.of(user);
    return securityUser;
  }
}
