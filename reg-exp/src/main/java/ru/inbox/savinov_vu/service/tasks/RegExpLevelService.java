package ru.inbox.savinov_vu.service.tasks;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.inbox.savinov_vu.interfaces.CRUDService;
import ru.inbox.savinov_vu.model.tasks.RegExpLevel;
import ru.inbox.savinov_vu.repository.tasks.RegExpLevelRepository;

import java.util.List;

@Service
public class RegExpLevelService implements CRUDService<RegExpLevel> {
    @Autowired
    RegExpLevelRepository regExpLevelRepository;


    @Override
    public void add(RegExpLevel regExpLevel) {
        regExpLevelRepository.saveAndFlush(regExpLevel);
    }

    @Override
    public List<RegExpLevel> getAll() {
        List<RegExpLevel> all = regExpLevelRepository.findAll();
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
}
