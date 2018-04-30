package ru.inbox.savinov_vu.controller.tasks;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.inbox.savinov_vu.interfaces.CRUDController;
import ru.inbox.savinov_vu.model.tasks.RegExpTask;
import ru.inbox.savinov_vu.service.tasks.RegExpTaskService;

import java.util.List;

@RestController
@RequestMapping(value = "/tasks/regexptask", consumes = MediaType.ALL_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
public class RegExpTaskController implements CRUDController<RegExpTask> {
    @Autowired
    RegExpTaskService regExpTaskService;


    @Override
    public void add(RegExpTask regExpTask) {
        regExpTaskService.add(regExpTask);
    }

    @Override
    public List<RegExpTask> getAll() {
        return regExpTaskService.getAll();
    }

    @Override
    public RegExpTask getById(Integer id) {
        return regExpTaskService.getById(id);
    }

    @Override
    public boolean delete(Integer id) {
        regExpTaskService.delete(id);
        return true;
    }

    @Override
    public RegExpTask update(RegExpTask regExpTask) {
        return regExpTaskService.update(regExpTask);
    }
}
