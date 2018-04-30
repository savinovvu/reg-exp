package ru.inbox.savinov_vu.model.users;

import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;
import ru.inbox.savinov_vu.model.tasks.Comment;
import ru.inbox.savinov_vu.model.tasks.RegExpLevel;
import ru.inbox.savinov_vu.model.tasks.Like;
import ru.inbox.savinov_vu.model.tasks.RegExpTask;

import javax.persistence.*;
import java.util.List;

@Entity
public class User {

    @Id
    public Integer id;

    public String name;

    public String password;

    @Enumerated(value = EnumType.STRING)
    public Role role;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "user")
    public List<Like> likes;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "user")
    public List<Comment> comments;

    @LazyCollection(LazyCollectionOption.FALSE)
    @ManyToMany
    @JoinTable(name = "user_solved-regexp-task",
            joinColumns = {
                    @JoinColumn(name = "user_id", referencedColumnName = "id")},
            inverseJoinColumns = {
                    @JoinColumn(name = "solved-regexp-task_id", referencedColumnName = "id")})
    public List<RegExpTask> solvedRegExpTasks;


    @LazyCollection(LazyCollectionOption.FALSE)
    @ManyToMany
    @JoinTable(name = "user_solved-regexp-level",
            joinColumns = {
                    @JoinColumn(name = "user_id", referencedColumnName = "id")},
            inverseJoinColumns = {
                    @JoinColumn(name = "solved-regexp-level_id", referencedColumnName = "id")})
    public List<RegExpLevel> solvedRegExpLevels;
}
