package ru.inbox.savinov_vu.app.tasks.level.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.inbox.savinov_vu.app.tasks.level.model.RegExpLevel;



@Repository
public interface RegExpLevelRepository extends JpaRepository<RegExpLevel, Integer> {

  RegExpLevel findByNumber(Integer id);

}
