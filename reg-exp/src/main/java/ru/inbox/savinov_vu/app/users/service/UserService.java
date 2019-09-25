package ru.inbox.savinov_vu.app.users.service;

import org.springframework.stereotype.Service;
import ru.inbox.savinov_vu.core.interfaces.OperationResulter;
import ru.inbox.savinov_vu.app.users.model.User;
import ru.inbox.savinov_vu.app.users.repository.UserRepository;

import javax.annotation.Resource;
import java.util.List;



@Service
public class UserService {

    @Resource
    private UserRepository userRepository;


    public OperationResulter<String> add(User user) {
        User savedUser = userRepository.saveAndFlush(user);
        return () -> "successfully added";
    }


    public List<User> getAll() {
        return userRepository.findAll();
    }


    public User getById(Integer id) {
        return userRepository.findById(id).get();
    }


    public boolean delete(Integer id) {
        userRepository.deleteById(id);
        return true;
    }


    public User update(User user) {
        return userRepository.saveAndFlush(user);
    }

}
