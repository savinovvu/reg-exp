package ru.inbox.savinov_vu.app.tasks.comment.model;


import ru.inbox.savinov_vu.app.tasks.task.model.RegExpTask;
import ru.inbox.savinov_vu.app.users.model.User;
import ru.inbox.savinov_vu.common.interfaces.entityInterfaces.Identify;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;



@Entity
public class Comment implements Identify {


    private Integer id;

    private String text;

    private User user;

    private RegExpTask regExpTask;


    @Id
    @SequenceGenerator(name = "GLOBAL_SEQ", sequenceName = "GLOBAL_SEQ", allocationSize = 1, initialValue = 1000)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "GLOBAL_SEQ")
    @Override
    public Integer getId() {
        return id;
    }


    public String getText() {
        return text;
    }


    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    public User getUser() {
        return user;
    }


    @ManyToOne
    @JoinColumn(name = "regexptask_id", nullable = false)
    public RegExpTask getRegExpTask() {
        return regExpTask;
    }


    public void setId(Integer id) {
        this.id = id;
    }


    public void setText(String text) {
        this.text = text;
    }


    public void setUser(User user) {
        this.user = user;
    }


    public void setRegExpTask(RegExpTask regExpTask) {
        this.regExpTask = regExpTask;
    }
}
