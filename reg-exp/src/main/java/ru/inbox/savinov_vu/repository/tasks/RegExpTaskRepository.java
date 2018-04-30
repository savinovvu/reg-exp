package ru.inbox.savinov_vu.repository.tasks;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.inbox.savinov_vu.model.tasks.RegExpTask;

@Repository
public interface RegExpTaskRepository extends JpaRepository<RegExpTask, Integer> {
}
