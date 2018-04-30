package ru.inbox.savinov_vu.model.tasks;

import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;
import ru.inbox.savinov_vu.interfaces.Identify;
import ru.inbox.savinov_vu.model.users.User;

import javax.persistence.*;
import java.util.List;

@Entity
public class RegExpLevel implements Identify {

    @Id
    public Integer id;

    public String description;


    @OneToMany(targetEntity = RegExpTask.class, cascade = CascadeType.REMOVE, fetch = FetchType.LAZY, mappedBy = "regExpLevel")
    public List<RegExpTask> regExpTasks;

    @LazyCollection(LazyCollectionOption.FALSE)
    @ManyToMany(mappedBy = "solvedRegExpLevels")
    public List<User> users;

    @Override
    public Integer getId() {
        return id;
    }
}
