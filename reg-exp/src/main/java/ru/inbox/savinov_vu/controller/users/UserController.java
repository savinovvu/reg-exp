package ru.inbox.savinov_vu.controller.users;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.inbox.savinov_vu.common.interfaces.CRUD.CRUDController;
import ru.inbox.savinov_vu.common.interfaces.OperationResulter;
import ru.inbox.savinov_vu.model.users.User;
import ru.inbox.savinov_vu.service.users.UserService;

import javax.servlet.http.HttpServletRequest;
import java.security.Principal;
import java.util.List;



@RestController
@RequestMapping(value = "/users/user", consumes = MediaType.ALL_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
public class UserController implements CRUDController<User> {

    @Value("${jwt.header}")
    private String tokenHeader;

    @Autowired
    UserService userService;


    @Override
    public ResponseEntity<OperationResulter<String>> add(HttpServletRequest request, User user, Principal principal) {
        return new ResponseEntity(userService.add(user), HttpStatus.ACCEPTED.OK);
    }


    @Override
    public ResponseEntity<List<User>> getAll(HttpServletRequest request, Principal principal) {
        return new ResponseEntity(userService.getAll(), HttpStatus.ACCEPTED.OK);
    }


    @Override
    public ResponseEntity<User> getById(HttpServletRequest request, @PathVariable("id") Integer id, Principal principal) {
        return new ResponseEntity(userService.getById(id), HttpStatus.ACCEPTED.OK);
    }


    @Override
    public ResponseEntity delete(HttpServletRequest request, @PathVariable("id") Integer id, Principal principal) {
        userService.delete(id);
        return new ResponseEntity(Boolean.TRUE, HttpStatus.ACCEPTED.OK);
    }


    @Override
    public ResponseEntity<User> update(HttpServletRequest request, User user, Principal principal) {
        return new ResponseEntity(userService.update(user), HttpStatus.ACCEPTED.OK);
    }


}
