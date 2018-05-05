package ru.inbox.savinov_vu.model.tasks;

import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;
import ru.inbox.savinov_vu.interfaces.Identify;
import ru.inbox.savinov_vu.model.users.User;

import javax.persistence.*;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Entity
public class RegExpTask implements Identify {


    private Integer id;

    private String name;

    private String description;

    private Set<String> matchStrings = new HashSet<>();

    private Set<String> excludedStrings = new HashSet<>();

    private Set<String> requiredSubStrings = new HashSet<>();

    private List<Like> likes;

    private List<Comment> comments;

    private RegExpLevel regExpLevel;

    private User author;

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

    public String getName() {
        return name;
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



    @ElementCollection(fetch = FetchType.EAGER)
    @CollectionTable(name = "matchStrings", joinColumns = @JoinColumn(name = "regexptask_id"))
    public Set<String> getMatchStrings() {
        return matchStrings;
    }

    @ElementCollection(fetch = FetchType.EAGER)
    @CollectionTable(name = "excludedStrings", joinColumns = @JoinColumn(name = "regexptask_id"))
    public Set<String> getExcludedStrings() {
        return excludedStrings;
    }

    @ElementCollection(fetch = FetchType.EAGER)
    @CollectionTable(name = "requiredSubStrings", joinColumns = @JoinColumn(name = "regexptask_id"))
    public Set<String> getRequiredSubStrings() {
        return requiredSubStrings;
    }

    @LazyCollection(LazyCollectionOption.TRUE)
    @ManyToMany(mappedBy = "solvedRegExpTasks")
    public List<User> getUsers() {
        return users;
    }

    @ManyToOne
    @JoinColumn(name = "author_id", nullable = true)
    public User getAuthor() {
        return author;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setName(String name) {
        this.name = name;
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

    public void setMatchStrings(Set<String> matchStrings) {
        this.matchStrings = matchStrings;
    }

    public void setExcludedStrings(Set<String> excludedStrings) {
        this.excludedStrings = excludedStrings;
    }

    public void setRequiredSubStrings(Set<String> requiredSubStrings) {
        this.requiredSubStrings = requiredSubStrings;
    }

    public void setAuthor(User author) {
        this.author = author;
    }
}
