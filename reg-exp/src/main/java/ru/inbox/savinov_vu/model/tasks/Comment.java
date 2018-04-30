package ru.inbox.savinov_vu.model.tasks;

import ru.inbox.savinov_vu.interfaces.Identify;
import ru.inbox.savinov_vu.model.users.User;

import javax.persistence.*;

@Entity
public class Comment implements Identify {

    @Id
    public Integer id;

    public String text;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "user_id", nullable = false)
    public User user;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "regexptask_id", nullable = false)
    public RegExpTask regExpTask;

    @Override
    public Integer getId() {
        return id;
    }
}
