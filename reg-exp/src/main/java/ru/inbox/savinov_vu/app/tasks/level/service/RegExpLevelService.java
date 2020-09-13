package ru.inbox.savinov_vu.app.tasks.level.service;

import lombok.RequiredArgsConstructor;
import org.mapstruct.factory.Mappers;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.inbox.savinov_vu.app.tasks.level.dto.RegExpLevelDto;
import ru.inbox.savinov_vu.app.tasks.level.dto.RegExpLevelDtoMapper;
import ru.inbox.savinov_vu.app.tasks.level.model.RegExpLevel;
import ru.inbox.savinov_vu.app.tasks.level.model.RegExpLevel_;
import ru.inbox.savinov_vu.app.tasks.level.repository.RegExpLevelRepository;
import ru.inbox.savinov_vu.app.users.model.User;
import ru.inbox.savinov_vu.app.users.service.UserService;

import java.util.List;
import java.util.stream.Collectors;


@Service
@RequiredArgsConstructor
public class RegExpLevelService {

  private final RegExpLevelRepository regExpLevelRepository;

  private final UserService userService;


  private List<RegExpLevel> findAll() {
    List<RegExpLevel> all = regExpLevelRepository.findAll(Sort.by(Sort.Direction.ASC, RegExpLevel_.NUMBER));
    return all;
  }


  @Transactional(readOnly = true)
  public List<RegExpLevelDto> findAllForUser(Integer userId) {
    User user = userService.getById(userId);
    List<RegExpLevel> all = findAll();
    List<RegExpLevelDto> result = all.stream()
      .map(v -> Mappers.getMapper(RegExpLevelDtoMapper.class)
        .mapEntityToDto(v)
        .setSolve(v.getUsers().contains(user)))
      .collect(Collectors.toList());
    return result;
  }


  @Transactional(readOnly = true)
  public RegExpLevel findById(Integer id) {
    return regExpLevelRepository.findById(id).orElse(null);
  }

}
