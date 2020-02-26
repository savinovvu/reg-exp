package ru.inbox.savinov_vu.core.security;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import ru.inbox.savinov_vu.app.users.model.User;
import ru.inbox.savinov_vu.app.users.service.UserService;
import ru.inbox.savinov_vu.core.security.jwt.dto.SignUpDto;
import ru.inbox.savinov_vu.core.security.jwt.model.SecurityUser;

import javax.annotation.Resource;
import java.util.Optional;



@Service
public class SecurityService implements UserDetailsService {

  @Resource
  private UserService userService;


  @Override
  public UserDetails loadUserByUsername(String login) throws UsernameNotFoundException {
    User user = userService.getByLogin(login);
    return SecurityUser.of(user);
  }


  public Optional<SecurityUser> authenticate(String userName, String password) {
    User byLogin = userService.getByLogin(userName);
    return Optional.of(byLogin)
      .filter(user -> user.getPassword().equals(SecurityUtil.encryptSHA(password)))
      .map(SecurityUser::of);
  }


  public void signUp(SignUpDto signUpDto) {
    User user = new User();
    user.setFirstName(signUpDto.getFirstName());
    user.setLastName(signUpDto.getLastName());
    user.setLogin(signUpDto.getLogin());
    user.setEmail(signUpDto.getEmail());
    user.setSex(signUpDto.getSex());
    user.setBirthDate(signUpDto.getBirthDate());
    user.setPassword(SecurityUtil.encryptSHA(signUpDto.getPassword()));
    userService.add(user);
  }


}
