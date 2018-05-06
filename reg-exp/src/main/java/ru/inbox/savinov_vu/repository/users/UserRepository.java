package ru.inbox.savinov_vu.repository.users;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.inbox.savinov_vu.model.users.User;



@Repository
public interface UserRepository extends JpaRepository<User, Integer> {

}
