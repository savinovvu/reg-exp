package ru.inbox.savinov_vu.model.tasks;

import com.fasterxml.jackson.annotation.JsonIgnore;
import org.hibernate.annotations.*;
import ru.inbox.savinov_vu.interfaces.Identify;
import ru.inbox.savinov_vu.model.users.User;

import javax.persistence.*;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.Table;
import java.util.HashSet;
import java.util.List;
import java.util.Set;



@Entity
@Table(uniqueConstraints = {@UniqueConstraint(columnNames = {"regexplevel_id", "number"})})
public class RegExpTask implements Identify {


    private Integer id;

    private Integer number;

    private String name;

    private String description;

    private Set<String> matchStrings = new HashSet<>();

    private Set<String> excludedStrings = new HashSet<>();

    private Set<String> requiredSubStrings = new HashSet<>();

    private Set<String> excludedAnswers = new HashSet<>();

    private List<Like> likes;

    private List<Comment> comments;

    private RegExpLevel regExpLevel;

    private User author;

    private List<User> users;

    private List<RegExpTaskAnswer> answers;

    private Boolean enabled;

    private List<UsefulLinks> usefulLinks;


    @Id
    @SequenceGenerator(name = "GLOBAL_SEQ", sequenceName = "GLOBAL_SEQ", allocationSize = 1, initialValue = 1000)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "GLOBAL_SEQ")
    @Override
    public Integer getId() {
        return id;
    }


    public Integer getNumber() {
        return number;
    }


    public String getDescription() {
        return description;
    }


    public String getName() {
        return name;
    }


    @ManyToOne
    @JoinColumn(name = "regexplevel_id", nullable = true)
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
    @CollectionTable(joinColumns = @JoinColumn(name = "regexptask_id"))
    public Set<String> getMatchStrings() {
        return matchStrings;
    }


    @ElementCollection(fetch = FetchType.EAGER)
    @CollectionTable(joinColumns = @JoinColumn(name = "regexptask_id"))
    public Set<String> getExcludedStrings() {
        return excludedStrings;
    }


    @ElementCollection(fetch = FetchType.EAGER)
    @CollectionTable(joinColumns = @JoinColumn(name = "regexptask_id"))
    public Set<String> getRequiredSubStrings() {
        return requiredSubStrings;
    }


    @ElementCollection(fetch = FetchType.EAGER)
    @CollectionTable(joinColumns = @JoinColumn(name = "regexptask_id"))
    public Set<String> getExcludedAnswers() {
        return excludedAnswers;
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


    @LazyCollection(LazyCollectionOption.TRUE)
    @OneToMany(cascade = CascadeType.REMOVE, mappedBy = "regExpTask")
    public List<RegExpTaskAnswer> getAnswers() {
        return answers;
    }

    @LazyCollection(LazyCollectionOption.FALSE)
    @ManyToMany(mappedBy = "regExpTasks")
    public List<UsefulLinks> getUsefulLinks() {
        return usefulLinks;
    }


    @JsonIgnore
    public Boolean getEnabled() {
        return enabled;
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


    public void setExcludedAnswers(Set<String> excludedAnswers) {
        this.excludedAnswers = excludedAnswers;
    }


    public void setAuthor(User author) {
        this.author = author;
    }


    public void setAnswers(List<RegExpTaskAnswer> answers) {
        this.answers = answers;
    }


    public void setEnabled(Boolean enabled) {
        this.enabled = enabled;
    }


    public void setUsefulLinks(List<UsefulLinks> usefulLinks) {
        this.usefulLinks = usefulLinks;
    }
}
