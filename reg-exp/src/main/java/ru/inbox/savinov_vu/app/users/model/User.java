package ru.inbox.savinov_vu.app.users.model;

import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;
import ru.inbox.savinov_vu.app.tasks.comment.model.Comment;
import ru.inbox.savinov_vu.app.tasks.level.model.RegExpLevel;
import ru.inbox.savinov_vu.app.tasks.like.model.Like;
import ru.inbox.savinov_vu.app.tasks.task.model.RegExpTask;
import ru.inbox.savinov_vu.common.interfaces.entityInterfaces.Identify;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import java.util.Date;
import java.util.List;
import java.util.Set;


@Entity
@Table(name = "\"user\"")
public class User implements Identify {


    private Integer id;

    private String name;

    private String login;

    private String email;

    private String password;

    private Boolean enabled;

    private Set<Authority> authorities;

    private List<Like> likes;

    private List<Comment> comments;

    private List<RegExpTask> solvedRegExpTasks;

    private List<RegExpLevel> solvedRegExpLevels;

    private List<RegExpTask> addedTask;

    private Date lastPasswordResetDate;


    public User() {
    }


    public User(Integer id) {
        this.id = id;
    }


    @Id
    @SequenceGenerator(name = "GLOBAL_SEQ", sequenceName = "GLOBAL_SEQ", allocationSize = 1, initialValue = 1000)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "GLOBAL_SEQ")
    @Override
    public Integer getId() {
        return id;
    }


    public String getName() {
        return name;
    }


    public String getPassword() {
        return password;
    }


    @LazyCollection(LazyCollectionOption.TRUE)
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "user")
    public List<Like> getLikes() {
        return likes;
    }


    @LazyCollection(LazyCollectionOption.TRUE)
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "user")
    public List<Comment> getComments() {
        return comments;
    }


    @LazyCollection(LazyCollectionOption.TRUE)
    @ManyToMany
    @JoinTable(name = "user_solvedregexptask",
            joinColumns = {
                    @JoinColumn(name = "user_id", referencedColumnName = "id")},
            inverseJoinColumns = {
                    @JoinColumn(name = "solvedregexptask_id", referencedColumnName = "id")})
    public List<RegExpTask> getSolvedRegExpTasks() {
        return solvedRegExpTasks;
    }


    @LazyCollection(LazyCollectionOption.TRUE)
    @ManyToMany
    @JoinTable(name = "user_solvedregexplevel",
            joinColumns = {
                    @JoinColumn(name = "user_id", referencedColumnName = "id")},
            inverseJoinColumns = {
                    @JoinColumn(name = "solvedregexplevel_id", referencedColumnName = "id")})
    public List<RegExpLevel> getSolvedRegExpLevels() {
        return solvedRegExpLevels;
    }


    @LazyCollection(LazyCollectionOption.TRUE)
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "author")
    public List<RegExpTask> getAddedTask() {
        return addedTask;
    }


    @Column(unique = true)
    public String getLogin() {
        return login;
    }


    @Column(unique = true)
    public String getEmail() {
        return email;
    }


    @Temporal(TemporalType.TIMESTAMP)
    public Date getLastPasswordResetDate() {
        return lastPasswordResetDate;
    }


    public void setId(Integer id) {
        this.id = id;
    }


    public void setName(String name) {
        this.name = name;
    }


    public void setPassword(String password) {
        this.password = password;
    }


    public void setEnabled(Boolean enabled) {
        this.enabled = enabled;
    }


    public void setAuthorities(Set<Authority> authorities) {
        this.authorities = authorities;
    }


    public void setLikes(List<Like> likes) {
        this.likes = likes;
    }


    public void setComments(List<Comment> comments) {
        this.comments = comments;
    }


    public void setSolvedRegExpTasks(List<RegExpTask> solvedRegExpTasks) {
        this.solvedRegExpTasks = solvedRegExpTasks;
    }


    public void setSolvedRegExpLevels(List<RegExpLevel> solvedRegExpLevels) {
        this.solvedRegExpLevels = solvedRegExpLevels;
    }


    public void setAddedTask(List<RegExpTask> addedTask) {
        this.addedTask = addedTask;
    }


    public void setLogin(String login) {
        this.login = login;
    }


    public void setEmail(String email) {
        this.email = email;
    }


    public void setLastPasswordResetDate(Date lastPasswordResetDate) {
        this.lastPasswordResetDate = lastPasswordResetDate;
    }





}
