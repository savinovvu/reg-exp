package ru.inbox.savinov_vu.controller.tasks;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.inbox.savinov_vu.interfaces.CRUD.CRUDController;
import ru.inbox.savinov_vu.interfaces.OperationResulter;
import ru.inbox.savinov_vu.interfaces.numbered.NumberedController;
import ru.inbox.savinov_vu.model.tasks.RegExpLevel;
import ru.inbox.savinov_vu.service.tasks.RegExpLevelService;

import javax.servlet.http.HttpServletRequest;
import java.util.List;



@RestController
@RequestMapping(value = "/tasks/regexplevel", consumes = MediaType.ALL_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
public class RegExpLevelController implements CRUDController<RegExpLevel>, NumberedController<RegExpLevel> {

    @Autowired
    RegExpLevelService regExpLevelService;


    @Override
    public OperationResulter<String> add(HttpServletRequest request, RegExpLevel regExpLevel) {
        return regExpLevelService.add(regExpLevel);
    }


    @Override
    public List<RegExpLevel> getAll(HttpServletRequest request) {
        Integer userId = Integer.valueOf(request.getHeader("id"));
        return regExpLevelService.getAll(userId);
    }


    @Override
    public RegExpLevel getById(HttpServletRequest request, @PathVariable("id") Integer id) {
        return regExpLevelService.getById(id);
    }


    @Override
    public boolean delete(HttpServletRequest request, @PathVariable("id") Integer id) {
        regExpLevelService.delete(id);
        return true;
    }


    @Override
    public RegExpLevel update(HttpServletRequest request, RegExpLevel regExpLevel) {
        return regExpLevelService.update(regExpLevel);
    }


    @Override
    public RegExpLevel getByNumber(HttpServletRequest request,
                                   @PathVariable("number") Integer number) {
        return regExpLevelService.getByNumber(number);
    }
}
