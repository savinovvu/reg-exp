package ru.inbox.savinov_vu.controller.users;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.inbox.savinov_vu.common.interfaces.CRUD.CRUDController;
import ru.inbox.savinov_vu.common.interfaces.OperationResulter;
import ru.inbox.savinov_vu.model.users.User;
import ru.inbox.savinov_vu.service.users.UserService;

import javax.servlet.http.HttpServletRequest;
import java.util.List;



@RestController
@RequestMapping(value = "/users/user", consumes = MediaType.ALL_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
public class UserController implements CRUDController<User> {

    @Value("${jwt.header}")
    private String tokenHeader;

    @Autowired
    UserService userService;




    @Override
    public OperationResulter<String> add(HttpServletRequest request, User user) {
        return userService.add(user);
    }


    @Override
    public List<User> getAll(HttpServletRequest request) {
        return userService.getAll();
    }


    @Override
    public User getById(HttpServletRequest request, @PathVariable("id") Integer id) {
        return userService.getById(id);
    }


    @Override
    public boolean delete(HttpServletRequest request, @PathVariable("id") Integer id) {
        userService.delete(id);
        return true;
    }


    @Override
    public User update(HttpServletRequest request, User user) {
        return userService.update(user);
    }


}
