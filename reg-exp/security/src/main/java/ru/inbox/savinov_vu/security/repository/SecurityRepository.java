package ru.inbox.savinov_vu.security.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.inbox.savinov_vu.security.model.SecurityUser;



@Repository
public interface SecurityRepository extends JpaRepository<SecurityUser, Integer> {

    SecurityUser getByLogin(String login);
}
