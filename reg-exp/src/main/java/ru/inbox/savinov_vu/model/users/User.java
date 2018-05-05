package ru.inbox.savinov_vu.model.users;

import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;
import ru.inbox.savinov_vu.interfaces.Identify;
import ru.inbox.savinov_vu.model.tasks.Comment;
import ru.inbox.savinov_vu.model.tasks.Like;
import ru.inbox.savinov_vu.model.tasks.RegExpLevel;
import ru.inbox.savinov_vu.model.tasks.RegExpTask;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "\"user\"")
public class User implements Identify {


    private Integer id;

    private String name;

    private String login;

    private String email;

    private String password;

    private Role role;

    private List<Like> likes;

    private List<Comment> comments;

    private List<RegExpTask> solvedRegExpTasks;

    private List<RegExpLevel> solvedRegExpLevels;

    private List<RegExpTask> addedTask;

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


    @Enumerated(value = EnumType.STRING)
    public Role getRole() {
        return role;
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


    public String getLogin() {
        return login;
    }


    public String getEmail() {
        return email;
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

    public void setRole(Role role) {
        this.role = role;
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
}
