package ru.inbox.savinov_vu.core.security.service;

import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.inbox.savinov_vu.app.users.model.User;
import ru.inbox.savinov_vu.app.users.repository.UserRepository;
import ru.inbox.savinov_vu.app.users.service.UserService;
import ru.inbox.savinov_vu.core.exception.AuthenticationException;
import ru.inbox.savinov_vu.core.exception.FlowException;
import ru.inbox.savinov_vu.core.security.jwt.dto.SignUpDto;
import ru.inbox.savinov_vu.core.security.jwt.model.LogoutToken;
import ru.inbox.savinov_vu.core.security.jwt.model.SecurityUser;
import ru.inbox.savinov_vu.core.security.repository.LogoutTokenRepository;

import javax.annotation.Resource;
import java.util.Optional;

import static ru.inbox.savinov_vu.common.constant.StringConstants.GUEST_LOGIN;



@Service
@RequiredArgsConstructor
public class SecurityService implements UserDetailsService {


  @Resource
  private final UserService userService;

  @Resource
  private final UserRepository userRepository;

  @Resource
  private final BCryptPasswordEncoder encoder;

  @Resource
  private final LogoutTokenRepository logoutTokenRepository;


  @Override
  public UserDetails loadUserByUsername(String login) throws UsernameNotFoundException {
    User user = userService.getByLogin(login);
    return SecurityUser.of(user);
  }


  public SecurityUser authenticate(String userName, String password) {
    User byLogin = userService.getByLogin(userName);
    return Optional.ofNullable(byLogin)
      .filter(user -> encoder.matches(password, user.getPassword()))
      .map(SecurityUser::of)
      .orElseThrow(() -> new AuthenticationException("Invalid login/password for user " + userName));
  }


  public SecurityUser authenticateGuest() {
    User guest = userService.getByLogin(GUEST_LOGIN);
    return Optional.ofNullable(guest)
      .map(SecurityUser::of)
      .orElseThrow(() -> new AuthenticationException("Invalid login/password for user " + GUEST_LOGIN));
  }


  @Transactional
  public User signUp(SignUpDto signUpDto) {
    User user = new User();
    user.setFirstName(signUpDto.getFirstName());
    user.setLastName(signUpDto.getLastName());
    user.setLogin(signUpDto.getLogin());
    user.setEmail(signUpDto.getEmail());
    user.setSex(signUpDto.getSex());
    user.setBirthDate(signUpDto.getBirthDate());
    user.setPassword(signUpDto.getPassword());
    User result = signUp(user);
    return result;
  }


  @Transactional
  public User signUp(User user) {
    user.setPassword(encoder.encode(user.getPassword()));
    User result = userRepository.saveAndFlush(user);
    return result;
  }


  @Transactional
  public void logout(LogoutToken logoutToken) {
    logoutTokenRepository.saveAndFlush(logoutToken);
  }


  @Transactional(readOnly = true)
  public boolean isLogoutToken(String token) {
    Optional<LogoutToken> logoutToken = logoutTokenRepository.findByToken(token);
    return logoutToken.isPresent();
  }


  @Transactional(readOnly = true)
  public User getUserByPrincipal(Object principal) {
    if (principal instanceof UsernamePasswordAuthenticationToken) {
      UsernamePasswordAuthenticationToken token = (UsernamePasswordAuthenticationToken) principal;
      principal = token.getPrincipal();
    }


    if (principal instanceof SecurityUser) {
      SecurityUser secUser = (SecurityUser) principal;
      User user = userService.getById(secUser.getId());
      return user;
    }
    throw new FlowException("principal is not SecurityUser");
  }


}
