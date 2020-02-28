package ru.inbox.savinov_vu.testhelpers.data.init;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.inbox.savinov_vu.app.users.model.User;
import ru.inbox.savinov_vu.app.users.repository.UserRepository;
import ru.inbox.savinov_vu.app.users.service.UserService;
import ru.inbox.savinov_vu.testhelpers.data.factories.user.UserFactory;

import javax.annotation.Resource;
import java.util.List;
import java.util.stream.Collectors;

import static ru.inbox.savinov_vu.testhelpers.data.factories.user.UserFactory.getOne;



@Service
@RequiredArgsConstructor
public class UserInitializer {

  @Resource
  private final UserService userService;

  @Resource
  private final UserRepository userRepository;


  public User initOne() {
    userRepository.deleteAll();
    User user = getOne();
    user = userService.add(user);
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

}
