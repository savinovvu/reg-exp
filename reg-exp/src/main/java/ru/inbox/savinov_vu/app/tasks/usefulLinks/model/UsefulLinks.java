package ru.inbox.savinov_vu.app.tasks.usefulLinks.model;

import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;
import ru.inbox.savinov_vu.app.tasks.task.model.RegExpTask;
import ru.inbox.savinov_vu.common.interfaces.entityInterfaces.Identify;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.SequenceGenerator;
import java.util.List;



@Entity
public class UsefulLinks implements Identify {

    private Integer id;

    private String link;

    private String description;

    private List<RegExpTask> regExpTasks;


    @Id
    @SequenceGenerator(name = "GLOBAL_SEQ", sequenceName = "GLOBAL_SEQ", allocationSize = 1, initialValue = 1000)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "GLOBAL_SEQ")
    @Override
    public Integer getId() {
        return id;
    }


    public String getDescription() {
        return description;
    }


    public String getLink() {
        return link;
    }


    @LazyCollection(LazyCollectionOption.TRUE)
    @ManyToMany
    @JoinTable(name = "usefullnks_regexptask",
            joinColumns = {
                    @JoinColumn(name = "userfullinks_id", referencedColumnName = "id")},
            inverseJoinColumns = {
                    @JoinColumn(name = "regexptask_id", referencedColumnName = "id")})
    public List<RegExpTask> getRegExpTasks() {
        return regExpTasks;
    }


    public void setId(Integer id) {
        this.id = id;
    }


    public void setLink(String link) {
        this.link = link;
    }


    public void setDescription(String description) {
        this.description = description;
    }


    public void setRegExpTasks(List<RegExpTask> regExpTasks) {
        this.regExpTasks = regExpTasks;
    }
}
