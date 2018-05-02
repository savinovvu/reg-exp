package ru.inbox.savinov_vu.service.users;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.inbox.savinov_vu.interfaces.CRUD.CRUDService;
import ru.inbox.savinov_vu.model.users.User;
import ru.inbox.savinov_vu.repository.users.UserRepository;

import java.util.List;

@Service
public class UserService implements CRUDService<User> {
    @Autowired
    UserRepository userRepository;


    @Override
    public void add(User user) {
        userRepository.saveAndFlush(user);
    }

    @Override
    public List<User> getAll() {
        return userRepository.findAll();
    }

    @Override
    public User getById(Integer id) {
        return userRepository.findById(id).get();
    }

    @Override
    public boolean delete(Integer id) {
        userRepository.deleteById(id);
        return true;
    }

    @Override
    public User update(User user) {
        return userRepository.saveAndFlush(user);
    }
}
