package ru.inbox.savinov_vu.app.users.service;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.inbox.savinov_vu.app.tasks.level.model.RegExpLevel;
import ru.inbox.savinov_vu.app.users.dto.UserDto;
import ru.inbox.savinov_vu.app.users.dto.UserDtoMapper;
import ru.inbox.savinov_vu.app.users.model.User;
import ru.inbox.savinov_vu.app.users.model.User_;
import ru.inbox.savinov_vu.app.users.repository.UserFilter;
import ru.inbox.savinov_vu.app.users.repository.UserRepository;
import ru.inbox.savinov_vu.common.paged.PagedResultList;

import javax.annotation.Resource;
import java.util.List;
import java.util.Set;



@Service
@RequiredArgsConstructor
public class UserService {

  @Resource
  private final UserRepository userRepository;

  @Resource
  private final UserDtoMapper userDtoMapper;


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

  @Transactional(readOnly = true)
  public PagedResultList<UserDto> getByFilter(UserFilter filter) {
    PageRequest pageRequest = PageRequest.of(filter.getPage(), filter.getSize(), Sort.by(Sort.Direction.ASC, User_.ID));
    Page<User> all = userRepository.findAll(filter, pageRequest);
    PagedResultList<UserDto> userDtoPagedResultList = PagedResultList.ofPage(all, userDtoMapper);
    return userDtoPagedResultList;
  }


  @Transactional(readOnly = true)
  public Set<RegExpLevel> findSolvedLevels(Integer userId) {
    return userRepository.findSolvedLevels(userId);
  }


  @Transactional
  public User add(User user) {
    User result = userRepository.saveAndFlush(user);
    return result;
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
