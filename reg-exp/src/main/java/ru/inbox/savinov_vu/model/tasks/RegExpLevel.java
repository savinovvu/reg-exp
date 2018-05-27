package ru.inbox.savinov_vu.model.tasks;

import com.fasterxml.jackson.annotation.JsonGetter;
import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;
import org.hibernate.proxy.HibernateProxyHelper;
import org.springframework.context.annotation.Lazy;
import ru.inbox.savinov_vu.interfaces.Identify;
import ru.inbox.savinov_vu.model.users.User;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;
import java.util.Objects;



@Entity
public class RegExpLevel implements Identify, Serializable {

    private Integer id;

    private Integer number;

    private String description;

    private List<RegExpTask> regExpTasks;

    @OrderBy("name ASC")
    private List<User> users;

    private Boolean solve = false;


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


    @Transient
    @JsonGetter(value = "solve")
    public Boolean isSolve() {
        return solve;
    }

   // for Jackson serialization
    public Boolean getSolve() {
        return solve;
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


    public void setSolve(Boolean solve) {
        this.solve = solve;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != HibernateProxyHelper.getClassWithoutInitializingProxy(o)) return false;
        RegExpLevel that = (RegExpLevel) o;
        return id.equals(that.id);
    }


    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
