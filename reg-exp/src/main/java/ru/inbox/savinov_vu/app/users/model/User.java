package ru.inbox.savinov_vu.app.users.model;

import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;
import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;
import ru.inbox.savinov_vu.app.tasks.comment.model.Comment;
import ru.inbox.savinov_vu.app.tasks.level.model.RegExpLevel;
import ru.inbox.savinov_vu.app.tasks.like.model.Like;
import ru.inbox.savinov_vu.app.tasks.task.model.RegExpTask;
import ru.inbox.savinov_vu.common.audit.BaseEntityAudit;
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
import java.time.LocalDateTime;
import java.util.List;



@Entity
@Table(name = "users")
@Getter
@Setter
@Accessors(chain = true)
public class User extends BaseEntityAudit implements Identify {

  @Id
  @SequenceGenerator(name = "GLOBAL_SEQ", sequenceName = "GLOBAL_SEQ", allocationSize = 1, initialValue = 1000)
  @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "GLOBAL_SEQ")
  private Integer id;

  private String name;

  @Column(unique = true)
  private String login;

  @Column(unique = true)
  private String email;

  private String password;

  private Boolean enabled;

//  private Set<Authority> authorities;

  @LazyCollection(LazyCollectionOption.TRUE)
  @OneToMany(cascade = CascadeType.ALL, mappedBy = "user")
  private List<Like> likes;

  @LazyCollection(LazyCollectionOption.TRUE)
  @OneToMany(cascade = CascadeType.ALL, mappedBy = "user")
  private List<Comment> comments;

  @LazyCollection(LazyCollectionOption.TRUE)
  @ManyToMany
  @JoinTable(name = "solved_regexp_tasks",
    joinColumns = {
      @JoinColumn(name = "user_id", referencedColumnName = "id")},
    inverseJoinColumns = {
      @JoinColumn(name = "regexp_task_id", referencedColumnName = "id")})
  private List<RegExpTask> solvedRegExpTasks;

  @LazyCollection(LazyCollectionOption.TRUE)
  @ManyToMany
  @JoinTable(name = "solved_regexp_levels",
    joinColumns = {
      @JoinColumn(name = "user_id", referencedColumnName = "id")},
    inverseJoinColumns = {
      @JoinColumn(name = "regexp_level_id", referencedColumnName = "id")})
  private List<RegExpLevel> solvedRegExpLevels;

  @LazyCollection(LazyCollectionOption.TRUE)
  @OneToMany(cascade = CascadeType.ALL, mappedBy = "author")
  private List<RegExpTask> addedTask;

  private LocalDateTime lastPasswordResetDate;


  public User(Integer id) {
    this.id = id;
    this.enabled = true;
  }


  public User() {
    this.enabled = true;
  }


}
