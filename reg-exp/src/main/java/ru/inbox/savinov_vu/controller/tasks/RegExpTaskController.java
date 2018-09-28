package ru.inbox.savinov_vu.controller.tasks;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.inbox.savinov_vu.checker.taskChecker.TaskCheckerController;
import ru.inbox.savinov_vu.common.interfaces.CRUD.CRUDController;
import ru.inbox.savinov_vu.common.interfaces.OperationResulter;
import ru.inbox.savinov_vu.common.interfaces.numbered.NumberedController;
import ru.inbox.savinov_vu.model.tasks.RegExpTask;
import ru.inbox.savinov_vu.model.users.User;
import ru.inbox.savinov_vu.service.tasks.RegExpTaskService;

import javax.servlet.http.HttpServletRequest;
import java.security.Principal;
import java.util.List;

import static ru.inbox.savinov_vu.common.constant.PathConstant.REGEXP_TASK;



@RestController
@RequestMapping(value = REGEXP_TASK, consumes = MediaType.ALL_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
public class RegExpTaskController implements CRUDController<RegExpTask>, TaskCheckerController<RegExpTask>, NumberedController<RegExpTask> {

    @Autowired
    RegExpTaskService regExpTaskService;


    @Override
    @PostMapping
    public ResponseEntity<OperationResulter> add(HttpServletRequest request, @RequestBody RegExpTask regExpTask, Principal principal) {
        Integer userId = Integer.valueOf(request.getHeader("id"));
        regExpTask.setEnabled(false);
        regExpTask.setAuthor(new User(userId));
        return new ResponseEntity(regExpTaskService.add(regExpTask), HttpStatus.ACCEPTED.OK);
    }


    @Override
    public ResponseEntity<List<RegExpTask>> getAllByParentId(HttpServletRequest request, @PathVariable("id") Integer id, Principal principal) {
        Integer userId = Integer.valueOf(request.getHeader("id"));
        return new ResponseEntity(regExpTaskService.getAllByParentId(id, userId), HttpStatus.ACCEPTED.OK);
    }


    @Override
    public ResponseEntity<RegExpTask> getById(HttpServletRequest request, @PathVariable("id") Integer id, Principal principal) {
        return new ResponseEntity(regExpTaskService.getById(id), HttpStatus.ACCEPTED.OK);
    }


    @Override
    public ResponseEntity<Boolean> delete(HttpServletRequest request, @PathVariable("id") Integer id, Principal principal) {
        regExpTaskService.delete(id);
        return new ResponseEntity(Boolean.TRUE, HttpStatus.ACCEPTED.OK);
    }


    @Override
    public ResponseEntity<RegExpTask> update(HttpServletRequest request, RegExpTask regExpTask, Principal principal) {
        return new ResponseEntity(regExpTaskService.update(regExpTask), HttpStatus.ACCEPTED.OK);
    }


    @Override
    public ResponseEntity check(HttpServletRequest request,
                                @PathVariable("id") Integer id,
                                @RequestBody String answer,
                                Principal principal) {
        return new ResponseEntity(regExpTaskService.check(id, answer), HttpStatus.ACCEPTED.OK);
    }


    @Override
    public ResponseEntity<List<RegExpTask>> getDisabledTask(Principal principal) {
        return new ResponseEntity(regExpTaskService.getDisabledTask(), HttpStatus.ACCEPTED.OK);
    }


    @Override
    public ResponseEntity getByParentNumberAndByNumber(HttpServletRequest request,
                                                       @PathVariable("parentNumber") Integer parentNumber,
                                                       @PathVariable("number") Integer number,
                                                       Principal principal) {
        return new ResponseEntity(regExpTaskService.getByParentNumberAndByNumber(parentNumber, number),
                HttpStatus.ACCEPTED.OK);
    }

}
