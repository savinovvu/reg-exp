package ru.inbox.savinov_vu.model.tasks;

import ru.inbox.savinov_vu.model.users.User;

import javax.persistence.*;

@Entity
public class Like {

    @Id
    public Integer id;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "user_id", nullable = false)
    public User user;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "regexp-task_id", nullable = false)
    public RegExpTask regExpTask;
}
