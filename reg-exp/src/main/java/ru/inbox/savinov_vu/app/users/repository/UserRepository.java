package ru.inbox.savinov_vu.app.users.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import ru.inbox.savinov_vu.app.tasks.level.model.RegExpLevel;
import ru.inbox.savinov_vu.app.tasks.task.model.RegExpTask;
import ru.inbox.savinov_vu.app.users.model.User;

import java.util.Set;



@Repository
public interface UserRepository extends JpaRepository<User, Integer>, JpaSpecificationExecutor<User> {

  @Query("select u from User u where u.login=:login")
  User getByLogin(@Param("login") String login);

  boolean existsByLogin(String login);

  @Query("select u.solvedRegExpLevels from User u where u.id =:userId")
  Set<RegExpLevel> findSolvedLevels(@Param(("userId")) Integer userId);

  @Query("select u.solvedRegExpTasks from User u where u.id =:userId")
  Set<RegExpTask> findSolvedTasks(@Param(("userId")) Integer userId);

  User findByLogin(String login);

  @Override
  Page<User> findAll(Specification<User> spec, Pageable pageable);



}
