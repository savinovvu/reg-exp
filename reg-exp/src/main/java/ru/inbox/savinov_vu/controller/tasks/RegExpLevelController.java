package ru.inbox.savinov_vu.controller.tasks;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.inbox.savinov_vu.interfaces.CRUDController;
import ru.inbox.savinov_vu.model.tasks.RegExpLevel;
import ru.inbox.savinov_vu.service.tasks.RegExpLevelService;

import java.util.List;

@RestController
@RequestMapping(value = "/tasks/regexplevel", consumes = MediaType.ALL_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
public class RegExpLevelController implements CRUDController<RegExpLevel> {
    @Autowired
    RegExpLevelService regExpLevelService;

    @Override
    public void add(RegExpLevel regExpLevel) {
        regExpLevelService.add(regExpLevel);
    }

    @Override
    public List<RegExpLevel> getAll() {
        return regExpLevelService.getAll();
    }

    @Override
    public RegExpLevel getById(Integer id) {
        return regExpLevelService.getById(id);
    }

    @Override
    public boolean delete(Integer id) {
        regExpLevelService.delete(id);
        return true;
    }

    @Override
    public RegExpLevel update(RegExpLevel regExpLevel) {
        return regExpLevelService.update(regExpLevel);
    }
}
