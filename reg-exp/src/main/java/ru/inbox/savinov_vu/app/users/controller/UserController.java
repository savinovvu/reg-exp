package ru.inbox.savinov_vu.app.users.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.inbox.savinov_vu.core.interfaces.OperationResulter;
import ru.inbox.savinov_vu.app.users.model.User;
import ru.inbox.savinov_vu.app.users.service.UserService;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.security.Principal;
import java.util.List;



@RestController
public class UserController {


    @Resource
    private UserService userService;


    @PostMapping("/v1/users/user")
    public ResponseEntity<OperationResulter<String>> add(HttpServletRequest request, User user, Principal principal) {
        return new ResponseEntity(userService.add(user), HttpStatus.ACCEPTED.OK);
    }


    @GetMapping("/v1/users/user")
    public ResponseEntity<List<User>> getAll(HttpServletRequest request, Principal principal) {
        return new ResponseEntity(userService.getAll(), HttpStatus.ACCEPTED.OK);
    }


    @GetMapping("/v1/users/user/{id}")
    public ResponseEntity<User> getById(HttpServletRequest request, @PathVariable("id") Integer id, Principal principal) {
        return new ResponseEntity(userService.getById(id), HttpStatus.ACCEPTED.OK);
    }


    @DeleteMapping("/v1/users/user/{id}")
    public ResponseEntity delete(HttpServletRequest request, @PathVariable("id") Integer id, Principal principal) {
        userService.delete(id);
        return new ResponseEntity(Boolean.TRUE, HttpStatus.ACCEPTED.OK);
    }


    @PutMapping("/v1/users/user")
    public ResponseEntity<User> update(HttpServletRequest request, User user, Principal principal) {
        return new ResponseEntity(userService.update(user), HttpStatus.ACCEPTED.OK);
    }


}
