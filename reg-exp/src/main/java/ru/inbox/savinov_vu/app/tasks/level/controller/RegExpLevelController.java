package ru.inbox.savinov_vu.app.tasks.level.controller;

import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import ru.inbox.savinov_vu.app.tasks.level.dto.RegExpLevelDto;
import ru.inbox.savinov_vu.app.tasks.level.model.RegExpLevel;
import ru.inbox.savinov_vu.app.tasks.level.service.RegExpLevelService;
import ru.inbox.savinov_vu.common.util.HttpRequestUtil;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.List;



@RestController
@AllArgsConstructor
public class RegExpLevelController {

  @Resource
  private final RegExpLevelService regExpLevelService;


  @GetMapping("/v1/tasks/regexplevel")
  public ResponseEntity<List<RegExpLevelDto>> getAll(HttpServletRequest request) {
    Integer userId = HttpRequestUtil.getUserId(request);
    List<RegExpLevelDto> regExpLevelDtos = regExpLevelService.findAllForUser(userId);
    return new ResponseEntity(regExpLevelDtos, HttpStatus.OK);
  }


  @GetMapping("/v1/tasks/regexplevel/{id}")
  public ResponseEntity<RegExpLevel> getById(@PathVariable("id") Integer id) {
    RegExpLevel byId = regExpLevelService.findById(id);
    return new ResponseEntity(byId, HttpStatus.OK);
  }


}
