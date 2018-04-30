package ru.inbox.savinov_vu.model.tasks;

import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;
import ru.inbox.savinov_vu.interfaces.Identify;
import ru.inbox.savinov_vu.model.users.User;

import javax.persistence.*;
import java.util.List;

@Entity
public class RegExpTask implements Identify {


    private Integer id;

    private String description;

    @ElementCollection(fetch = FetchType.EAGER)
    @CollectionTable(name = "matchStrings", joinColumns = @JoinColumn(name = "regexptask_id"))
    private List<String> matchStrings;

    @ElementCollection(fetch = FetchType.EAGER)
    @CollectionTable(name = "excludedStrings", joinColumns = @JoinColumn(name = "regexptask_id"))
    private List<String> excludedStrings;

    @ElementCollection(fetch = FetchType.EAGER)
    @CollectionTable(name = "requiredSymbols", joinColumns = @JoinColumn(name = "regexptask_id"))
    private List<String> requiredSymbols;

    private List<Like> likes;

    private List<Comment> comments;


    private RegExpLevel regExpLevel;

    private List<User> users;


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

    @ManyToOne
    @JoinColumn(name = "regexplevel_id", nullable = false)
    public RegExpLevel getRegExpLevel() {
        return regExpLevel;
    }

    @LazyCollection(LazyCollectionOption.TRUE)
    @OneToMany(cascade = CascadeType.REMOVE, mappedBy = "regExpTask")
    public List<Like> getLikes() {
        return likes;
    }

    @LazyCollection(LazyCollectionOption.TRUE)
    @OneToMany(cascade = CascadeType.REMOVE, mappedBy = "regExpTask")
    public List<Comment> getComments() {
        return comments;
    }

    @ElementCollection(fetch = FetchType.LAZY)
    @CollectionTable(name = "matchStrings", joinColumns = @JoinColumn(name = "regexptask_id"))
    public List<String> getMatchStrings() {
        return matchStrings;
    }
    @ElementCollection(fetch = FetchType.LAZY)
    @CollectionTable(name = "excludedStrings", joinColumns = @JoinColumn(name = "regexptask_id"))
    public List<String> getExcludedStrings() {
        return excludedStrings;
    }
    @ElementCollection(fetch = FetchType.LAZY)
    @CollectionTable(name = "requiredSymbols", joinColumns = @JoinColumn(name = "regexptask_id"))
    public List<String> getRequiredSymbols() {
        return requiredSymbols;
    }

    @LazyCollection(LazyCollectionOption.TRUE)
    @ManyToMany(mappedBy = "solvedRegExpTasks")
    public List<User> getUsers() {
        return users;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setLikes(List<Like> likes) {
        this.likes = likes;
    }

    public void setComments(List<Comment> comments) {
        this.comments = comments;
    }

    public void setRegExpLevel(RegExpLevel regExpLevel) {
        this.regExpLevel = regExpLevel;
    }

    public void setUsers(List<User> users) {
        this.users = users;
    }

    public void setMatchStrings(List<String> matchStrings) {
        this.matchStrings = matchStrings;
    }

    public void setExcludedStrings(List<String> excludedStrings) {
        this.excludedStrings = excludedStrings;
    }

    public void setRequiredSymbols(List<String> requiredSymbols) {
        this.requiredSymbols = requiredSymbols;
    }
}
