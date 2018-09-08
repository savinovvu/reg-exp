package ru.inbox.savinov_vu.controller.tasks;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import ru.inbox.savinov_vu.checker.TaskResulter;
import ru.inbox.savinov_vu.common.interfaces.CRUD.CRUDController;
import ru.inbox.savinov_vu.common.interfaces.OperationResulter;
import ru.inbox.savinov_vu.common.interfaces.numbered.NumberedController;
import ru.inbox.savinov_vu.checker.taskChecker.TaskCheckerController;
import ru.inbox.savinov_vu.model.tasks.RegExpTask;
import ru.inbox.savinov_vu.model.users.User;
import ru.inbox.savinov_vu.service.tasks.RegExpTaskService;

import javax.servlet.http.HttpServletRequest;
import java.util.List;



@RestController
@RequestMapping(value = "/tasks/regexptask", consumes = MediaType.ALL_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
public class RegExpTaskController implements CRUDController<RegExpTask>, TaskCheckerController<RegExpTask>, NumberedController<RegExpTask> {

    @Autowired
    RegExpTaskService regExpTaskService;


    @Override
    @PostMapping
    public OperationResulter add(HttpServletRequest request, @RequestBody RegExpTask regExpTask) {
        Integer userId = Integer.valueOf(request.getHeader("id"));
        regExpTask.setEnabled(false);
        regExpTask.setAuthor(new User(userId));
        return regExpTaskService.add(regExpTask);
    }


    @Override
    public List<RegExpTask> getAllByParentId(HttpServletRequest request, @PathVariable("id") Integer id) {
        Integer userId = Integer.valueOf(request.getHeader("id"));
        return regExpTaskService.getAllByParentId(id, userId);
    }


    @Override
    public RegExpTask getById(HttpServletRequest request, @PathVariable("id") Integer id) {
        return regExpTaskService.getById(id);
    }


    @Override
    public boolean delete(HttpServletRequest request, @PathVariable("id") Integer id) {
        regExpTaskService.delete(id);
        return true;
    }


    @Override
    public RegExpTask update(HttpServletRequest request, RegExpTask regExpTask) {
        return regExpTaskService.update(regExpTask);
    }


    @Override
    public TaskResulter check(HttpServletRequest request,
                              @PathVariable("id") Integer id,
                              @RequestBody String answer) {
        return regExpTaskService.check(id, answer);
    }


    @Override
    public List<RegExpTask> getDisabledTask() {
        return regExpTaskService.getDisabledTask();
    }


    @Override
    public RegExpTask getByParentNumberAndByNumber(HttpServletRequest request,
                                                   @PathVariable("parentNumber") Integer parentNumber,
                                                   @PathVariable("number") Integer number) {
        return regExpTaskService.getByParentNumberAndByNumber(parentNumber, number);
    }

}
