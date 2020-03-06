package ru.inbox.savinov_vu.app.tasks.level.service;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.inbox.savinov_vu.app.tasks.level.model.RegExpLevel;
import ru.inbox.savinov_vu.app.tasks.level.model.RegExpLevel_;
import ru.inbox.savinov_vu.app.tasks.level.repository.RegExpLevelRepository;

import javax.annotation.Resource;
import java.util.List;



@Service
@RequiredArgsConstructor
public class RegExpLevelService {

  @Resource
  private final RegExpLevelRepository regExpLevelRepository;


  @Transactional(readOnly = true)
  public List<RegExpLevel> findAll() {
    List<RegExpLevel> all = regExpLevelRepository.findAll(Sort.by(Sort.Direction.ASC, RegExpLevel_.NUMBER));
    return all;

  }


  @Transactional(readOnly = true)
  public RegExpLevel findById(Integer id) {
    return regExpLevelRepository.findById(id).orElse(null);
  }

}
