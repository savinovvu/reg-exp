package ru.inbox.savinov_vu.model.tasks;

import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;
import ru.inbox.savinov_vu.interfaces.Identify;
import ru.inbox.savinov_vu.model.users.User;

import javax.persistence.*;
import java.util.List;

@Entity
public class RegExpTask implements Identify {

    @Id
    public Integer id;

    public String description;

/*    @CollectionTable(name = "matchStrings")
    public List<String> matchStrings;*/

  /*  @CollectionTable(name = "excludedStrings")
    public List<String> excludedStrings;*/

 /*   @CollectionTable(name = "requiredSymbols")
    public List<String> requiredSymbols;*/

    @OneToMany(cascade = CascadeType.REMOVE, fetch = FetchType.LAZY, mappedBy = "regExpTask")
    public List<Like> likes;

    @OneToMany(targetEntity = Comment.class, cascade = CascadeType.REMOVE, fetch = FetchType.LAZY, mappedBy = "regExpTask")
    public List<Comment> comments;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "regexplevel_id", nullable = false)
    public RegExpLevel regExpLevel;

    @LazyCollection(LazyCollectionOption.FALSE)
    @ManyToMany(mappedBy = "solvedRegExpTasks")
    public List<User> users;

    @Override
    public Integer getId() {
        return id;
    }

}
