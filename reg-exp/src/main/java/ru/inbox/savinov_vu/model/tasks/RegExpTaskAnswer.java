package ru.inbox.savinov_vu.model.tasks;

import ru.inbox.savinov_vu.interfaces.Identify;

import javax.persistence.*;



@Entity
@Table(uniqueConstraints = {@UniqueConstraint(columnNames = {"regExpTask_id", "answer"})})
public class RegExpTaskAnswer implements Identify {

    private Integer id;

    private String answer;

    private RegExpTask regExpTask;


    @Id
    @SequenceGenerator(name = "GLOBAL_SEQ", sequenceName = "GLOBAL_SEQ", allocationSize = 1, initialValue = 1000)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "GLOBAL_SEQ")
    public Integer getId() {
        return id;
    }


    public String getAnswer() {
        return answer;
    }


    @ManyToOne
    @JoinColumn(name = "regExpTask_id", nullable = false)
    public RegExpTask getRegExpTask() {
        return regExpTask;
    }


    public void setId(Integer id) {
        this.id = id;
    }


    public void setAnswer(String answer) {
        this.answer = answer;
    }


    public void setRegExpTask(RegExpTask regExpTask) {
        this.regExpTask = regExpTask;
    }


}
