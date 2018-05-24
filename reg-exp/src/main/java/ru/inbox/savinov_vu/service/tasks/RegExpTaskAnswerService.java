package ru.inbox.savinov_vu.service.tasks;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.inbox.savinov_vu.interfaces.CRUD.CRUDService;
import ru.inbox.savinov_vu.interfaces.OperationResulter;
import ru.inbox.savinov_vu.model.tasks.RegExpTaskAnswer;
import ru.inbox.savinov_vu.repository.tasks.RegExpTaskAnswerRepository;



@Service
public class RegExpTaskAnswerService implements CRUDService<RegExpTaskAnswer> {

    @Autowired
    RegExpTaskAnswerRepository repository;


    @Override
    public OperationResulter<String> add(RegExpTaskAnswer regExpTaskAnswer) {
        repository.saveAndFlush(regExpTaskAnswer);
        return () -> "successfully added";
    }
}
