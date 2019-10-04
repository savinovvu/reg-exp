package ru.inbox.savinov_vu.app.users.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.inbox.savinov_vu.core.interfaces.OperationResulter;
import ru.inbox.savinov_vu.app.users.model.User;
import ru.inbox.savinov_vu.app.users.repository.UserRepository;

import javax.annotation.Resource;
import java.util.List;

import static ru.inbox.savinov_vu.common.constant.StringConstants.SUCCESSFULLY_ADDED;



@Service
@RequiredArgsConstructor
public class UserService {

  @Resource
  private final UserRepository userRepository;

  @Transactional(readOnly = true)
  public List<User> getAll() {
    return userRepository.findAll();
  }


  @Transactional(readOnly = true)
  public User getById(Integer id) {
    return userRepository.findById(id).get();
  }


  @Transactional(readOnly = true)
  public User getByLogin(String login) {
    User user = userRepository.findByLogin(login);
    return user;
  }


  @Transactional
  public OperationResulter<String> add(User user) {
    userRepository.saveAndFlush(user);
    return () -> SUCCESSFULLY_ADDED;
  }


  @Transactional
  public User update(User user) {
    return userRepository.saveAndFlush(user);
  }


  @Transactional
  public boolean delete(Integer id) {
    userRepository.deleteById(id);
    return true;
  }


}
