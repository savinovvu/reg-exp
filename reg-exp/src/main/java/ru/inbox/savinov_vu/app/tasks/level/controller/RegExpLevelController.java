package ru.inbox.savinov_vu.app.tasks.level.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.inbox.savinov_vu.core.interfaces.OperationResulter;
import ru.inbox.savinov_vu.app.tasks.level.model.RegExpLevel;
import ru.inbox.savinov_vu.app.tasks.level.service.RegExpLevelService;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.security.Principal;
import java.util.List;



@RestController
public class RegExpLevelController  {

    @Resource
    private RegExpLevelService regExpLevelService;

    @PostMapping("/v1/tasks/regexplevel")
    public ResponseEntity<OperationResulter<String>> add(HttpServletRequest request, RegExpLevel regExpLevel, Principal principal) {
        return new ResponseEntity(regExpLevelService.add(regExpLevel), HttpStatus.ACCEPTED.OK);
    }

    @GetMapping("/v1/tasks/regexplevel")
    public ResponseEntity<List<RegExpLevel>> getAll(HttpServletRequest request, Principal principal) {
        Integer userId = Integer.valueOf(request.getHeader("id"));
        return new ResponseEntity(regExpLevelService.getAll(userId), HttpStatus.ACCEPTED.OK);
    }

    @GetMapping("/v1/tasks/regexplevel/{id}")
    public ResponseEntity<RegExpLevel> getById(HttpServletRequest request, @PathVariable("id") Integer id, Principal principal) {
        return new ResponseEntity(regExpLevelService.getById(id), HttpStatus.ACCEPTED.OK);
    }

    @DeleteMapping("/v1/tasks/regexplevel/{id}")
    public ResponseEntity<Boolean> delete(HttpServletRequest request, @PathVariable("id") Integer id, Principal principal) {
        regExpLevelService.delete(id);
        return new ResponseEntity(Boolean.TRUE, HttpStatus.ACCEPTED.OK);
    }


    @PutMapping("/v1/tasks/regexplevel")
    public ResponseEntity<RegExpLevel> update(HttpServletRequest request, RegExpLevel regExpLevel, Principal principal) {
        return new ResponseEntity(regExpLevelService.update(regExpLevel), HttpStatus.ACCEPTED.OK);
    }

    @GetMapping("/v1/tasks/regexplevel/byNumber/{number}")
    public ResponseEntity<RegExpLevel> getByNumber(HttpServletRequest request, @PathVariable("number") Integer number,
                                                   Principal principal) {
        return new ResponseEntity(regExpLevelService.getByNumber(number), HttpStatus.ACCEPTED.OK);
    }
}
