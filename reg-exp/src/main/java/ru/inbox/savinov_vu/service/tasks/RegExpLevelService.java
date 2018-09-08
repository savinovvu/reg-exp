package ru.inbox.savinov_vu.service.tasks;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import ru.inbox.savinov_vu.common.interfaces.CRUD.CRUDService;
import ru.inbox.savinov_vu.common.interfaces.OperationResulter;
import ru.inbox.savinov_vu.common.interfaces.numbered.NumberedService;
import ru.inbox.savinov_vu.model.tasks.RegExpLevel;
import ru.inbox.savinov_vu.repository.tasks.RegExpLevelRepository;
import ru.inbox.savinov_vu.repository.users.UserRepository;

import java.util.List;
import java.util.Set;



@Service
public class RegExpLevelService implements CRUDService<RegExpLevel>, NumberedService<RegExpLevel> {

    @Autowired
    RegExpLevelRepository regExpLevelRepository;

    @Autowired
    UserRepository userRepository;


    @Override
    public OperationResulter<String> add(RegExpLevel regExpLevel) {
        regExpLevelRepository.saveAndFlush(regExpLevel);
        return () -> "successfully added";
    }


    @Override
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


    @Override
    public RegExpLevel getById(Integer id) {
        return regExpLevelRepository.findById(id).get();
    }


    @Override
    public boolean delete(Integer id) {
        regExpLevelRepository.deleteById(id);
        return true;
    }


    @Override
    public RegExpLevel update(RegExpLevel regExpLevel) {
        return regExpLevelRepository.saveAndFlush(regExpLevel);
    }


    @Override
    public RegExpLevel getByNumber(Integer number) {
        return regExpLevelRepository.getByNumber(number);
    }
}
