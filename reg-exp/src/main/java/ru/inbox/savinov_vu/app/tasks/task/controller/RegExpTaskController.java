package ru.inbox.savinov_vu.app.tasks.task.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import ru.inbox.savinov_vu.core.interfaces.OperationResulter;
import ru.inbox.savinov_vu.app.tasks.task.model.RegExpTask;
import ru.inbox.savinov_vu.app.users.model.User;
import ru.inbox.savinov_vu.app.tasks.task.service.RegExpTaskService;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.security.Principal;
import java.util.List;



@RestController
public class RegExpTaskController {

    @Resource
    private RegExpTaskService regExpTaskService;


    @PostMapping("/v1/tasks/regexptask")
    public ResponseEntity<OperationResulter> add(HttpServletRequest request, @RequestBody RegExpTask regExpTask, Principal principal) {
        Integer userId = Integer.valueOf(request.getHeader("id"));
        regExpTask.setEnabled(false);
        regExpTask.setAuthor(new User(userId));
        return new ResponseEntity(regExpTaskService.add(regExpTask), HttpStatus.ACCEPTED.OK);
    }


    @GetMapping("/v1/tasks/regexptask/parent/{id}")
    public ResponseEntity<List<RegExpTask>> getAllByParentId(HttpServletRequest request, @PathVariable("id") Integer id, Principal principal) {
        Integer userId = Integer.valueOf(request.getHeader("id"));
        return new ResponseEntity(regExpTaskService.getAllByParentId(id, userId), HttpStatus.ACCEPTED.OK);
    }


    @GetMapping("/v1/tasks/regexptask/{id}")
    public ResponseEntity<RegExpTask> getById(@PathVariable("id") Integer id, Principal principal) {
        return new ResponseEntity(regExpTaskService.getById(id), HttpStatus.ACCEPTED.OK);
    }


    @DeleteMapping("/v1/tasks/regexptask/{id}")
    public ResponseEntity<Boolean> delete(@PathVariable("id") Integer id, Principal principal) {
        regExpTaskService.delete(id);
        return new ResponseEntity(Boolean.TRUE, HttpStatus.ACCEPTED.OK);
    }


    @PutMapping("/v1/tasks/regexptask")
    public ResponseEntity<RegExpTask> update(RegExpTask regExpTask, Principal principal) {
        return new ResponseEntity(regExpTaskService.update(regExpTask), HttpStatus.ACCEPTED.OK);
    }


    @PutMapping("/v1/tasks/regexptask/check/{id}")
    public ResponseEntity check(@PathVariable("id") Integer id,
                                @RequestBody String answer) {
        return new ResponseEntity(regExpTaskService.check(id, answer), HttpStatus.ACCEPTED.OK);
    }


    @GetMapping("/v1/tasks/regexptask/check/{id}")
    public ResponseEntity<List<RegExpTask>> getDisabledTask(Principal principal) {
        return new ResponseEntity(regExpTaskService.getDisabledTask(), HttpStatus.ACCEPTED.OK);
    }


    @GetMapping("/v1/tasks/regexplevel/byNumber/{parentNumber}/{number}")
    public ResponseEntity getByParentNumberAndByNumber(@PathVariable("parentNumber") Integer parentNumber,
                                                       @PathVariable("number") Integer number) {
        return new ResponseEntity(regExpTaskService.getByParentNumberAndByNumber(parentNumber, number),
                HttpStatus.ACCEPTED.OK);
    }

}
