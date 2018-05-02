package ru.inbox.savinov_vu.repository.tasks;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.inbox.savinov_vu.model.tasks.RegExpTask;

import java.util.List;

@Repository
public interface RegExpTaskRepository extends JpaRepository<RegExpTask, Integer> {
    List<RegExpTask> getByRegExpLevelId(Integer id);
}
