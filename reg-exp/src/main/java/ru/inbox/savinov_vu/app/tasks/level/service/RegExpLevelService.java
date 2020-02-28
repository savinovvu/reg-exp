package ru.inbox.savinov_vu.app.tasks.level.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.inbox.savinov_vu.app.tasks.level.model.RegExpLevel;
import ru.inbox.savinov_vu.app.tasks.level.repository.RegExpLevelRepository;
import ru.inbox.savinov_vu.app.users.service.UserService;

import javax.annotation.Resource;
import java.util.List;



@Service
@RequiredArgsConstructor
public class RegExpLevelService {

  @Resource
  private final RegExpLevelRepository regExpLevelRepository;

  @Resource
  private final UserService userService;

  // todo implement with criteria api or querydsl
  @Transactional(readOnly = true)
  public List<RegExpLevel> findAll(Integer userId) {
    return null;

  }


  @Transactional(readOnly = true)
  public RegExpLevel findById(Integer id) {
    return regExpLevelRepository.findById(id).orElse(null);
  }


  @Transactional(readOnly = true)
  public RegExpLevel findByNumber(Integer number) {
    return regExpLevelRepository.findByNumber(number);
  }


  @Transactional
  public RegExpLevel create(RegExpLevel regExpLevel) {
    RegExpLevel result = regExpLevelRepository.saveAndFlush(regExpLevel);
    return result;
  }


  @Transactional
  public RegExpLevel update(RegExpLevel regExpLevel) {
    return regExpLevelRepository.saveAndFlush(regExpLevel);
  }


  @Transactional
  public boolean delete(Integer id) {
    regExpLevelRepository.deleteById(id);
    return true;
  }
}
