package ru.inbox.savinov_vu.model;

import ru.inbox.savinov_vu.service.RegExpTaskService;

import javax.persistence.Entity;
import javax.persistence.Id;
import java.util.List;

@Entity
public class Level {

    @Id
    public int id;

    public List<RegExpTaskService> regExpTasks;
}
