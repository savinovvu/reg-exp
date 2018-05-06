package ru.inbox.savinov_vu.model.tasks;

import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;
import org.springframework.context.annotation.Lazy;
import ru.inbox.savinov_vu.interfaces.Identify;
import ru.inbox.savinov_vu.model.users.User;

import javax.persistence.*;
import java.util.List;

@Entity
public class RegExpLevel implements Identify {

    private Integer id;

    private Integer number;

    private String description;

    private List<RegExpTask> regExpTasks;

    @OrderBy("name ASC")
    private List<User> users;

    @Id
    @SequenceGenerator(name = "GLOBAL_SEQ", sequenceName = "GLOBAL_SEQ", allocationSize = 1, initialValue = 1000)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "GLOBAL_SEQ")
    @Override
    public Integer getId() {
        return id;
    }

    @Column(unique = true)
    public Integer getNumber() {
        return number;
    }

    public String getDescription() {
        return description;
    }

    @LazyCollection(LazyCollectionOption.TRUE)
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "regExpLevel")
    @Lazy
    public List<RegExpTask> getRegExpTasks() {
        return regExpTasks;
    }

    @LazyCollection(LazyCollectionOption.TRUE)
    @ManyToMany(mappedBy = "solvedRegExpLevels")
    public List<User> getUsers() {
        return users;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public void setNumber(Integer number) {
        this.number = number;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setRegExpTasks(List<RegExpTask> regExpTasks) {
        this.regExpTasks = regExpTasks;
    }

    public void setUsers(List<User> users) {
        this.users = users;
    }
}
