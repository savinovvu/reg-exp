package ru.inbox.savinov_vu.service.tasks;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.inbox.savinov_vu.checker.RegExpTaskCheckerUtil;
import ru.inbox.savinov_vu.checker.TaskResulter;
import ru.inbox.savinov_vu.interfaces.CRUD.CRUDService;
import ru.inbox.savinov_vu.interfaces.TaskChecker.TaskCheckerService;
import ru.inbox.savinov_vu.model.tasks.RegExpTask;
import ru.inbox.savinov_vu.repository.tasks.RegExpTaskRepository;

import java.util.List;

@Service
public class RegExpTaskService implements CRUDService<RegExpTask>, TaskCheckerService {
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
    public List<RegExpTask> getAllByParentId(Integer id) {
        return regExpTaskRepository.getByRegExpLevelId(id);
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

    @Override
//    todo после авторизации сделать проверку на решенные все задачи у уровня, а также поставить флаг решения задач
    public TaskResulter check(Integer id, String answer) {
        RegExpTask checkedTask = getById(id);
        TaskResulter result = RegExpTaskCheckerUtil.check(checkedTask, answer);
        return result;
    }
}
