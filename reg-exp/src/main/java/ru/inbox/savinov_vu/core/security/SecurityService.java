package ru.inbox.savinov_vu.core.security;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import ru.inbox.savinov_vu.app.users.model.Authority;
import ru.inbox.savinov_vu.app.users.model.User;
import ru.inbox.savinov_vu.app.users.service.UserService;

import javax.annotation.Resource;
import java.util.List;
import java.util.Optional;



@Service
public class SecurityService implements UserDetailsService {

  @Resource
  private UserService userService;


  @Override
  public UserDetails loadUserByUsername(String login) throws UsernameNotFoundException {
    User user = userService.getByLogin(login);
    return new org.springframework.security.core.userdetails.User(user.getLogin(), user.getPassword(), List.of(Authority.USER, Authority.ADMIN));
  }


  public Optional<org.springframework.security.core.userdetails.User> authenticate(String userName, String password) {
    User byLogin = userService.getByLogin(userName);
    return Optional.of(byLogin)
      .filter(user -> user.getPassword().equals(SecurityUtil.encryptSHA(password)))
      .map(user -> new org.springframework.security.core.userdetails.User(user.getLogin(), user.getPassword(), List.of(Authority.USER, Authority.ADMIN)));
  }


}
