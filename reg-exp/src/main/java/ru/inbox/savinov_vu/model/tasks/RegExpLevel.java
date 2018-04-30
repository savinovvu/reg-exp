package ru.inbox.savinov_vu.model.tasks;

import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;
import ru.inbox.savinov_vu.model.users.User;

import javax.persistence.*;
import java.util.List;

@Entity
public class RegExpLevel {

    @Id
    public int id;


    @OneToMany(cascade = CascadeType.REMOVE, fetch = FetchType.LAZY, mappedBy = "regExpLevel")
    public List<RegExpTask> regExpTasks;

    @LazyCollection(LazyCollectionOption.FALSE)
    @ManyToMany(mappedBy = "solvedRegExpLevels")
    public List<User> users;
}
