package ru.inbox.savinov_vu.app.tasks.taskAnswer.service;

import org.springframework.stereotype.Service;
import ru.inbox.savinov_vu.core.interfaces.OperationResulter;
import ru.inbox.savinov_vu.app.tasks.taskAnswer.model.RegExpTaskAnswer;
import ru.inbox.savinov_vu.app.tasks.taskAnswer.repository.RegExpTaskAnswerRepository;

import javax.annotation.Resource;



@Service
public class RegExpTaskAnswerService {

    @Resource
    private RegExpTaskAnswerRepository repository;


    public OperationResulter<String> add(RegExpTaskAnswer regExpTaskAnswer) {
        repository.saveAndFlush(regExpTaskAnswer);
        return () -> "successfully added";
    }
}
