package ru.inbox.savinov_vu.model.tasks;

import ru.inbox.savinov_vu.interfaces.Identify;
import ru.inbox.savinov_vu.model.users.User;

import javax.persistence.*;



@Entity
@Table(name = "\"like\"")
public class Like implements Identify {


    private Integer id;

    private User user;

    private RegExpTask regExpTask;


    @Id
    @SequenceGenerator(name = "GLOBAL_SEQ", sequenceName = "GLOBAL_SEQ", allocationSize = 1, initialValue = 1000)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "GLOBAL_SEQ")
    @Override
    public Integer getId() {
        return id;
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


    public void setUser(User user) {
        this.user = user;
    }


    public void setRegExpTask(RegExpTask regExpTask) {
        this.regExpTask = regExpTask;
    }
}
