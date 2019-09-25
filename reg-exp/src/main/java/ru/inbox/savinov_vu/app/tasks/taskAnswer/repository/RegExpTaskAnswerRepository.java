package ru.inbox.savinov_vu.app.tasks.taskAnswer.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.inbox.savinov_vu.app.tasks.taskAnswer.model.RegExpTaskAnswer;



public interface RegExpTaskAnswerRepository extends JpaRepository<RegExpTaskAnswer, Integer> {

}
