package ru.inbox.savinov_vu.service.tasks;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.inbox.savinov_vu.interfaces.CRUDService;
import ru.inbox.savinov_vu.model.tasks.RegExpTask;
import ru.inbox.savinov_vu.repository.tasks.RegExpTaskRepository;

import java.util.List;

@Service
public class RegExpTaskService implements CRUDService<RegExpTask> {
    @Autowired
    RegExpTaskRepository regExpTaskRepository;


    @Override
    public void add(RegExpTask regExpTask) {
        regExpTaskRepository.saveAndFlush(regExpTask);
    }

    @Override
    public List<RegExpTask> getAll() {
        return regExpTaskRepository.findAll();
    }

    @Override
    public RegExpTask getById(Integer id) {
        return regExpTaskRepository.findById(id).get();
    }

    @Override
    public boolean delete(Integer id) {
        regExpTaskRepository.deleteById(id);
        return true;
    }

    @Override
    public RegExpTask update(RegExpTask regExpTask) {
        return regExpTaskRepository.saveAndFlush(regExpTask);
    }
}
