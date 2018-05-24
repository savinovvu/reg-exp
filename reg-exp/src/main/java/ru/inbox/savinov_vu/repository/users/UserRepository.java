package ru.inbox.savinov_vu.repository.users;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import ru.inbox.savinov_vu.model.users.User;



@Repository
public interface UserRepository extends JpaRepository<User, Integer> {

    @Query("select u from User u where u.login=:login")
    User getByLogin(@Param("login") String login);

    boolean existsByLogin(String username);
}
