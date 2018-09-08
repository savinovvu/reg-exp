package ru.inbox.savinov_vu.service.tasks;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.inbox.savinov_vu.checker.RegExpTaskCheckerUtil;
import ru.inbox.savinov_vu.checker.TaskResulter;
import ru.inbox.savinov_vu.common.interfaces.CRUD.CRUDService;
import ru.inbox.savinov_vu.common.interfaces.OperationResulter;
import ru.inbox.savinov_vu.common.interfaces.numbered.NumberedService;
import ru.inbox.savinov_vu.checker.taskChecker.TaskCheckerService;
import ru.inbox.savinov_vu.model.tasks.RegExpTask;
import ru.inbox.savinov_vu.model.tasks.RegExpTaskAnswer;
import ru.inbox.savinov_vu.repository.tasks.RegExpTaskRepository;
import ru.inbox.savinov_vu.repository.users.UserRepository;

import java.util.List;
import java.util.Set;



@Service
public class RegExpTaskService implements CRUDService<RegExpTask>, TaskCheckerService<RegExpTask>, NumberedService<RegExpTask> {

    @Autowired
    RegExpTaskRepository repository;

    @Autowired
    RegExpTaskAnswerService regExpTaskAnswerService;

    @Autowired
    UserRepository userRepository;


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
    public List<RegExpTask> getAllByParentId(Integer id, Integer userId) {
        List<RegExpTask> all = repository.getByRegExpLevelIdOrderByNumber(id);
        Set<RegExpTask> solvedLevels = userRepository.findSolvedTasks(userId);
        for (RegExpTask regExpTask : all) {
            if (solvedLevels.contains(regExpTask)) {
                regExpTask.setSolve(true);
            } else {
                regExpTask.setSolve(false);
            }
        }
        return all;
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
