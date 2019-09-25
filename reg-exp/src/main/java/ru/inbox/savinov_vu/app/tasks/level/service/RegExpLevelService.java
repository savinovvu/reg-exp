package ru.inbox.savinov_vu.app.tasks.level.service;

import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import ru.inbox.savinov_vu.core.interfaces.OperationResulter;
import ru.inbox.savinov_vu.app.tasks.level.model.RegExpLevel;
import ru.inbox.savinov_vu.app.tasks.level.repository.RegExpLevelRepository;
import ru.inbox.savinov_vu.app.users.repository.UserRepository;

import javax.annotation.Resource;
import java.util.List;
import java.util.Set;



@Service
public class RegExpLevelService {

    @Resource
    private RegExpLevelRepository regExpLevelRepository;

    @Resource
    private UserRepository userRepository;


    public OperationResulter<String> add(RegExpLevel regExpLevel) {
        regExpLevelRepository.saveAndFlush(regExpLevel);
        return () -> "successfully added";
    }


    public List<RegExpLevel> getAll(Integer userId) {
        List<RegExpLevel> all = regExpLevelRepository.findAll(new Sort(Sort.Direction.ASC, "number"));
        Set<RegExpLevel> solvedLevels = userRepository.findSolvedLevels(userId);
        for (RegExpLevel regExpLevel : all) {
            if (solvedLevels.contains(regExpLevel)) {
                regExpLevel.setSolve(true);
            } else {
                regExpLevel.setSolve(false);
            }
        }

        return all;

    }


    public RegExpLevel getById(Integer id) {
        return regExpLevelRepository.findById(id).get();
    }


    public boolean delete(Integer id) {
        regExpLevelRepository.deleteById(id);
        return true;
    }


    public RegExpLevel update(RegExpLevel regExpLevel) {
        return regExpLevelRepository.saveAndFlush(regExpLevel);
    }


    public RegExpLevel getByNumber(Integer number) {
        return regExpLevelRepository.getByNumber(number);
    }
}
