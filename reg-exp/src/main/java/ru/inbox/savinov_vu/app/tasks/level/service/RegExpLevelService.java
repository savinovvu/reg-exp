package ru.inbox.savinov_vu.app.tasks.level.service;

import lombok.AllArgsConstructor;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.inbox.savinov_vu.core.interfaces.OperationResulter;
import ru.inbox.savinov_vu.app.tasks.level.model.RegExpLevel;
import ru.inbox.savinov_vu.app.tasks.level.repository.RegExpLevelRepository;
import ru.inbox.savinov_vu.app.users.repository.UserRepository;

import javax.annotation.Resource;
import java.util.List;
import java.util.Set;

import static ru.inbox.savinov_vu.common.constant.StringConstants.SUCCESSFULLY_ADDED;



@Service
@AllArgsConstructor
public class RegExpLevelService {

  @Resource
  private final RegExpLevelRepository regExpLevelRepository;

  @Resource
  private final UserRepository userRepository;


  @Transactional(readOnly = true)
  public List<RegExpLevel> getAll(Integer userId) {
    List<RegExpLevel> all = regExpLevelRepository.findAll(new Sort(Sort.Direction.ASC, "number"));
    Set<RegExpLevel> solvedLevels = userRepository.findSolvedLevels(userId);
    for (RegExpLevel regExpLevel : all) {
      if (solvedLevels.contains(regExpLevel)) {
        regExpLevel.setSolve(true);
      } else {
        regExpLevel.setSolve(false);
      }
    }
    return all;

  }


  @Transactional(readOnly = true)
  public RegExpLevel getById(Integer id) {
    return regExpLevelRepository.findById(id).get();
  }


  @Transactional(readOnly = true)
  public RegExpLevel getByNumber(Integer number) {
    return regExpLevelRepository.getByNumber(number);
  }


  @Transactional
  public OperationResulter<String> add(RegExpLevel regExpLevel) {
    regExpLevelRepository.saveAndFlush(regExpLevel);
    return () -> SUCCESSFULLY_ADDED;
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
