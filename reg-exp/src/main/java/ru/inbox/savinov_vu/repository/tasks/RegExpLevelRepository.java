package ru.inbox.savinov_vu.repository.tasks;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.inbox.savinov_vu.model.tasks.RegExpLevel;

@Repository
public interface RegExpLevelRepository extends JpaRepository<RegExpLevel, Integer> {
}
