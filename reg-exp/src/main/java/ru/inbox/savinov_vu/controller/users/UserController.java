package ru.inbox.savinov_vu.controller.users;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.inbox.savinov_vu.interfaces.CRUD.CRUDController;
import ru.inbox.savinov_vu.model.users.User;
import ru.inbox.savinov_vu.service.users.UserService;

import java.util.List;



@RestController
@RequestMapping(value = "/users/user", consumes = MediaType.ALL_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
public class UserController implements CRUDController<User> {

    @Autowired
    UserService userService;


    @Override
    public void add(User user) {
        userService.add(user);
    }


    @Override
    public List<User> getAll() {
        return userService.getAll();
    }


    @Override
    public User getById(@PathVariable("id") Integer id) {
        return userService.getById(id);
    }


    @Override
    public boolean delete(@PathVariable("id") Integer id) {
        userService.delete(id);
        return true;
    }


    @Override
    public User update(User user) {
        return userService.update(user);
    }
}
