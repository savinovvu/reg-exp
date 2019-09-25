package ru.inbox.savinov_vu.app.tasks.task.service;

import org.springframework.stereotype.Service;
import ru.inbox.savinov_vu.app.tasks.taskAnswer.service.RegExpTaskAnswerService;
import ru.inbox.savinov_vu.app.checker.RegExpTaskChecker;
import ru.inbox.savinov_vu.app.checker.TaskResulter;
import ru.inbox.savinov_vu.core.interfaces.OperationResulter;
import ru.inbox.savinov_vu.app.tasks.task.model.RegExpTask;
import ru.inbox.savinov_vu.app.tasks.taskAnswer.model.RegExpTaskAnswer;
import ru.inbox.savinov_vu.app.tasks.task.repository.RegExpTaskRepository;
import ru.inbox.savinov_vu.app.users.repository.UserRepository;

import javax.annotation.Resource;
import java.util.List;
import java.util.Set;



@Service
public class RegExpTaskService {

    @Resource
    private RegExpTaskRepository repository;

    @Resource
    private RegExpTaskAnswerService regExpTaskAnswerService;

    @Resource
    private UserRepository userRepository;

    @Resource
    private RegExpTaskChecker regExpTaskChecker;


    public OperationResulter add(RegExpTask regExpTask) {

        String answer = regExpTask.getAnswers().stream()
                .map(RegExpTaskAnswer::getAnswer)
                .findAny()
                .orElse(null);

        TaskResulter check = regExpTaskChecker.check(regExpTask, answer);
        if (check.getSuccess()) {
            RegExpTask savedRegExpTask = repository.saveAndFlush(regExpTask);
            RegExpTaskAnswer regExpTaskAnswer = regExpTask.getAnswers().get(0);
            regExpTaskAnswerService.add(regExpTaskAnswer.setRegExpTask(savedRegExpTask));
        }
        return check;
    }


    public List<RegExpTask> getAll() {
        return repository.findAll();
    }


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


    public RegExpTask getById(Integer id) {
        return repository.findById(id).get();
    }


    public boolean delete(Integer id) {
        repository.deleteById(id);
        return true;
    }


    public RegExpTask update(RegExpTask regExpTask) {
        return repository.saveAndFlush(regExpTask);
    }


    public TaskResulter check(Integer id, String answer) {
        var checkedTask = getById(id);
        return regExpTaskChecker.check(checkedTask, answer);
    }


    public List<RegExpTask> getDisabledTask() {
        return repository.getByEnabledOrderById(false);
    }


    public RegExpTask getByParentNumberAndByNumber(Integer parentNumber, Integer number) {
        return repository.getTaskByLevelIdAndByNumber(parentNumber, number);
    }
}
