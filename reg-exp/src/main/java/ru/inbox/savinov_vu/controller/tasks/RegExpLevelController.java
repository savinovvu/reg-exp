package ru.inbox.savinov_vu.controller.tasks;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.inbox.savinov_vu.common.interfaces.CRUD.CRUDController;
import ru.inbox.savinov_vu.common.interfaces.OperationResulter;
import ru.inbox.savinov_vu.common.interfaces.numbered.NumberedController;
import ru.inbox.savinov_vu.model.tasks.RegExpLevel;
import ru.inbox.savinov_vu.service.tasks.RegExpLevelService;

import javax.servlet.http.HttpServletRequest;
import java.security.Principal;
import java.util.List;

import static ru.inbox.savinov_vu.common.constant.PathConstant.REGEXP_LEVEL;



@RestController
@RequestMapping(value = REGEXP_LEVEL, consumes = MediaType.ALL_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
public class RegExpLevelController implements CRUDController<RegExpLevel>, NumberedController<RegExpLevel> {

    @Autowired
    RegExpLevelService regExpLevelService;


    @Override
    public ResponseEntity<OperationResulter<String>> add(HttpServletRequest request, RegExpLevel regExpLevel, Principal principal) {
        return new ResponseEntity(regExpLevelService.add(regExpLevel), HttpStatus.ACCEPTED.OK);
    }


    @Override
    public ResponseEntity<List<RegExpLevel>> getAll(HttpServletRequest request, Principal principal) {
        Integer userId = Integer.valueOf(request.getHeader("id"));
        return new ResponseEntity(regExpLevelService.getAll(userId), HttpStatus.ACCEPTED.OK);
    }


    @Override
    public ResponseEntity<RegExpLevel> getById(HttpServletRequest request, @PathVariable("id") Integer id, Principal principal) {
        return new ResponseEntity(regExpLevelService.getById(id), HttpStatus.ACCEPTED.OK);
    }


    @Override
    public ResponseEntity<Boolean> delete(HttpServletRequest request, @PathVariable("id") Integer id, Principal principal) {
        regExpLevelService.delete(id);
        return new ResponseEntity(Boolean.TRUE, HttpStatus.ACCEPTED.OK);
    }


    @Override
    public ResponseEntity<RegExpLevel> update(HttpServletRequest request, RegExpLevel regExpLevel, Principal principal) {
        return new ResponseEntity(regExpLevelService.update(regExpLevel), HttpStatus.ACCEPTED.OK);
    }


    @Override
    public ResponseEntity<RegExpLevel> getByNumber(HttpServletRequest request, @PathVariable("number") Integer number,
                                                   Principal principal) {
        return new ResponseEntity(regExpLevelService.getByNumber(number), HttpStatus.ACCEPTED.OK);
    }
}
