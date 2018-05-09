package ru.inbox.savinov_vu.repository.tasks;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import ru.inbox.savinov_vu.model.tasks.RegExpTask;

import java.util.List;



@Repository
public interface RegExpTaskRepository extends JpaRepository<RegExpTask, Integer> {

    List<RegExpTask> getByRegExpLevelIdOrderByNumber(Integer id);


    List<RegExpTask> getByEnabledOrderById(Boolean enable);


    @Query("Select r FROM RegExpTask r WHERE r.regExpLevel.number=:level_number AND r.number=:number")
    RegExpTask getTaskByLevelIdAndByNumber(@Param("level_number") Integer levelNumber,
                                                 @Param("number") Integer number);

}
