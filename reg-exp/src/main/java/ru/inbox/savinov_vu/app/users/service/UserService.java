package ru.inbox.savinov_vu.app.users.service;

import lombok.RequiredArgsConstructor;
import org.mapstruct.factory.Mappers;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.inbox.savinov_vu.app.users.dto.UserDto;
import ru.inbox.savinov_vu.app.users.dto.UserDtoMapper;
import ru.inbox.savinov_vu.app.users.model.User;
import ru.inbox.savinov_vu.app.users.repository.UserFilter;
import ru.inbox.savinov_vu.app.users.repository.UserRepository;
import ru.inbox.savinov_vu.common.paged.PagedResultList;

import javax.annotation.Resource;
import java.util.List;



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
  public User getByLogin(String login) {
    User user = userRepository.findByLogin(login);
    return user;
  }

  @Transactional(readOnly = true)
  public User getById(Integer id) {
    User user = userRepository.findById(id).orElse(null);
    return user;
  }


  @Transactional(readOnly = true)
  public PagedResultList<UserDto> getByFilter(UserFilter filter) {
    PageRequest pageRequest = PageRequest.of(filter.getPage(), filter.getSize(), filter.getSort());
    Page<User> all = userRepository.findAll(filter, pageRequest);
    PagedResultList<UserDto> userDtoPagedResultList = PagedResultList.ofPage(all, Mappers.getMapper(UserDtoMapper.class));
    return userDtoPagedResultList;
  }


  @Transactional
  public User update(User user) {
    return userRepository.saveAndFlush(user);
  }


  @Transactional
  public boolean delete(Integer id) {
    User user = userRepository.findById(id).get();
    user.setEnabled(false);
    update(user);
    return true;
  }


}
