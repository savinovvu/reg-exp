package ru.inbox.savinov_vu.security.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import ru.inbox.savinov_vu.security.repository.SecurityRepository;



@Service
public class SecurityService implements UserDetailsService {

    @Autowired
    SecurityRepository repository;


    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return repository.getByLogin(username);
    }
}
