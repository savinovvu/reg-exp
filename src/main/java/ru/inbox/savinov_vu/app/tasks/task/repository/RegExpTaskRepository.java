package ru.inbox.savinov_vu.app.tasks.task.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import ru.inbox.savinov_vu.app.tasks.task.model.RegExpTask;

import java.util.List;



@Repository
public interface RegExpTaskRepository extends JpaRepository<RegExpTask, Integer> {

  @Query("Select r FROM RegExpTask r WHERE r.regExpLevel.number=:levelNumber and r.enabled=true ORDER BY r.number")
  List<RegExpTask> findByRegExpLevelNumberOrderByNumber(Integer levelNumber);


  List<RegExpTask> getByEnabledOrderById(Boolean enable);


  @Query("Select r FROM RegExpTask r WHERE r.regExpLevel.number=:levelNumber AND r.number=:taskNumber")
  RegExpTask findTaskByLevelNumberAndByNumber(@Param("levelNumber") Integer levelNumber,
                                              @Param("taskNumber") Integer taskNumber);

}
