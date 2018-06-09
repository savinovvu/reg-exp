package ru.inbox.savinov_vu.service.users;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import ru.inbox.savinov_vu.interfaces.CRUD.CRUDService;
import ru.inbox.savinov_vu.interfaces.OperationResulter;
import ru.inbox.savinov_vu.model.users.User;
import ru.inbox.savinov_vu.repository.users.UserRepository;

import java.util.List;



@Service
public class UserService implements CRUDService<User>, UserDetailsService {

    @Autowired
    UserRepository userRepository;


    @Override
    public OperationResulter<String> add(User user) {
        User savedUser = userRepository.saveAndFlush(user);
        return () -> "successfully added";
    }


    public User signup(User user) {
        User savedUser = userRepository.saveAndFlush(user);
        return user;
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

    // todo: split UserDetails and User. see CustomUserDetailService https://github.com/savinovvu/spring-boot-settings/commit/0c3c442de3ae48c3560b4102b01c585883cadae6
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return userRepository.getByLogin(username);
    }
}
