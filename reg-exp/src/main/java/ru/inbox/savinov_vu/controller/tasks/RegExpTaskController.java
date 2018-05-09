package ru.inbox.savinov_vu.controller.tasks;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.inbox.savinov_vu.checker.TaskResulter;
import ru.inbox.savinov_vu.interfaces.CRUD.CRUDController;
import ru.inbox.savinov_vu.interfaces.taskChecker.TaskCheckerController;
import ru.inbox.savinov_vu.interfaces.numbered.NumberedController;
import ru.inbox.savinov_vu.model.tasks.RegExpTask;
import ru.inbox.savinov_vu.service.tasks.RegExpTaskService;

import java.util.List;



@RestController
@RequestMapping(value = "/tasks/regexptask", consumes = MediaType.ALL_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
public class RegExpTaskController implements CRUDController<RegExpTask>, TaskCheckerController<RegExpTask>, NumberedController<RegExpTask> {

    @Autowired
    RegExpTaskService regExpTaskService;


    @Override
    public void add(@RequestBody RegExpTask regExpTask) {
        regExpTaskService.add(regExpTask);
    }


    @Override
    public List<RegExpTask> getAllByParentId(@PathVariable("id") Integer id) {
        return regExpTaskService.getAllByParentId(id);
    }


    @Override
    public RegExpTask getById(@PathVariable("id") Integer id) {
        return regExpTaskService.getById(id);
    }


    @Override
    public boolean delete(@PathVariable("id") Integer id) {
        regExpTaskService.delete(id);
        return true;
    }


    @Override
    public RegExpTask update(RegExpTask regExpTask) {
        return regExpTaskService.update(regExpTask);
    }


    @Override
    public TaskResulter check(@PathVariable("id") Integer id, @RequestBody String answer) {
        return regExpTaskService.check(id, answer);
    }


    @Override
    public List<RegExpTask> getDisabledTask() {
        return regExpTaskService.getDisabledTask();
    }


    @Override
    public RegExpTask getByParentNumberAndByNumber(@PathVariable("parentNumber") Integer parentNumber,
                                                         @PathVariable("number") Integer number) {
        return regExpTaskService.getByParentNumberAndByNumber(parentNumber, number);
    }

}
