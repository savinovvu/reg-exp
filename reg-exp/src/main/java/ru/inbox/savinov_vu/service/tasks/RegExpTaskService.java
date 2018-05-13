package ru.inbox.savinov_vu.service.tasks;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.inbox.savinov_vu.checker.RegExpTaskCheckerUtil;
import ru.inbox.savinov_vu.checker.TaskResulter;
import ru.inbox.savinov_vu.interfaces.CRUD.CRUDService;
import ru.inbox.savinov_vu.interfaces.OperationResulter;
import ru.inbox.savinov_vu.interfaces.numbered.NumberedService;
import ru.inbox.savinov_vu.interfaces.taskChecker.TaskCheckerService;
import ru.inbox.savinov_vu.model.tasks.RegExpTask;
import ru.inbox.savinov_vu.model.tasks.RegExpTaskAnswer;
import ru.inbox.savinov_vu.repository.tasks.RegExpTaskRepository;

import java.util.List;



@Service
public class RegExpTaskService implements CRUDService<RegExpTask>, TaskCheckerService<RegExpTask>, NumberedService<RegExpTask> {

    @Autowired
    RegExpTaskRepository repository;

    @Autowired
    RegExpTaskAnswerService regExpTaskAnswerService;


    @Override
    public OperationResulter add(RegExpTask regExpTask) {

        String answer = regExpTask.getAnswers().stream()
                .map(RegExpTaskAnswer::getAnswer)
                .findAny()
                .orElse(null);

        TaskResulter check = RegExpTaskCheckerUtil.check(regExpTask, answer);
        if (check.getSuccess()) {
            RegExpTask savedRegExpTask = repository.saveAndFlush(regExpTask);
            RegExpTaskAnswer regExpTaskAnswer = regExpTask.getAnswers().get(0);
            regExpTaskAnswerService.add(regExpTaskAnswer.setRegExpTask(savedRegExpTask));
        }
        return check;
    }


    @Override
    public List<RegExpTask> getAll() {
        return repository.findAll();
    }


    @Override
    public List<RegExpTask> getAllByParentId(Integer id) {
        return repository.getByRegExpLevelIdOrderByNumber(id);
    }


    @Override
    public RegExpTask getById(Integer id) {
        return repository.findById(id).get();
    }


    @Override
    public boolean delete(Integer id) {
        repository.deleteById(id);
        return true;
    }


    @Override
    public RegExpTask update(RegExpTask regExpTask) {
        return repository.saveAndFlush(regExpTask);
    }


    @Override
    public TaskResulter check(Integer id, String answer) {
        var checkedTask = getById(id);
        return RegExpTaskCheckerUtil.check(checkedTask, answer);
    }


    @Override
    public List<RegExpTask> getDisabledTask() {
        return repository.getByEnabledOrderById(false);
    }


    @Override
    public RegExpTask getByParentNumberAndByNumber(Integer parentNumber, Integer number) {
        return repository.getTaskByLevelIdAndByNumber(parentNumber, number);
    }
}
