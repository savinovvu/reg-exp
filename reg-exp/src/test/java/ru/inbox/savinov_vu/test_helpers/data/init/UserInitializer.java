package ru.inbox.savinov_vu.test_helpers.data.init;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.inbox.savinov_vu.app.users.model.User;
import ru.inbox.savinov_vu.app.users.repository.UserRepository;
import ru.inbox.savinov_vu.app.users.service.UserService;
import ru.inbox.savinov_vu.core.security.SecurityService;
import ru.inbox.savinov_vu.test_helpers.data.factories.user.UserFactory;

import javax.annotation.Resource;
import java.util.List;
import java.util.stream.Collectors;

import static ru.inbox.savinov_vu.test_helpers.data.factories.user.UserFactory.getOne;



@Service
@RequiredArgsConstructor
public class UserInitializer {

  @Resource
  private final UserService userService;

  @Resource
  private final UserRepository userRepository;

  @Resource
  private final SecurityService securityService;


  public User initOne() {
    userRepository.deleteAll();
    User user = getOne();
    user = securityService.signUp(user);
    return user;
  }


  public User initOne(User user) {
    userRepository.deleteAll();
    user = securityService.signUp(user);
    return user;
  }


  public List<User> initOneList(List<User> userList) {
    userRepository.deleteAll();
    return userList.stream().map(userRepository::save).collect(Collectors.toList());
  }


  public List<User> initOneList() {
    userRepository.deleteAll();
    List<User> differentUserList = UserFactory.getDifferentUserList();
    return differentUserList.stream().map(userRepository::save).collect(Collectors.toList());
  }




  public User getEnabledUserFromExisting() {
    List<User> enabled = getEnabledUsersFromExisting();
    User user = enabled.get(0);
    return user;
  }


  public List<User> getEnabledUsersFromExisting() {
    List<User> all = userRepository.findAll();
    return all.stream().filter(v -> v.getEnabled()).collect(Collectors.toList());
  }


  public void deleteAll() {
    userRepository.deleteAll();
  }


  public User getById(Integer id) {
    User user = userService.getById(id);
    return user;
  }


}
