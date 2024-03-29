package ru.inbox.savinov_vu.app.users.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;
import lombok.experimental.FieldNameConstants;
import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;
import ru.inbox.savinov_vu.app.tasks.comment.model.Comment;
import ru.inbox.savinov_vu.app.tasks.level.model.RegExpLevel;
import ru.inbox.savinov_vu.app.tasks.like.model.Like;
import ru.inbox.savinov_vu.app.tasks.task.model.RegExpTask;
import ru.inbox.savinov_vu.common.audit.BaseEntityAudit;
import ru.inbox.savinov_vu.common.interfaces.entityInterfaces.Identify;
import ru.inbox.savinov_vu.core.converter.SexConverter;

import javax.persistence.*;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;



@Entity
@Table(name = "users")
@Getter
@Setter
@Accessors(chain = true)
@FieldNameConstants
public class User extends BaseEntityAudit implements Identify {

  @Id
  @SequenceGenerator(name = "users_seq", sequenceName = "users_seq", allocationSize = 1, initialValue = 1000)
  @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "users_seq")
  private Integer id;

  @Column(name = "first_name")
  private String firstName;

  @Column(name = "last_name")
  private String lastName;

  @Column(unique = true)
  private String login;

  @Column(unique = true)
  private String email;

  private String password;

  private Boolean enabled;

  private int score;

  @Column
  @Convert(converter = SexConverter.class)
  private Sex sex;

  @Column(name = "birth_date")
  @JsonProperty("birth_date")
  private LocalDate birthDate;


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


  @Transient
  public String getFullName() {
    return firstName + " " + lastName;
  }


  public User() {
    this.enabled = true;
  }


  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;
    if (!super.equals(o)) return false;
    User user = (User) o;
    return Objects.equals(id, user.id);
  }


  @Override
  public int hashCode() {
    return Objects.hash(super.hashCode(), id);
  }


  public void solveTask(RegExpTask task) {
    List<RegExpTask> solvedRegExpTasks = getSolvedRegExpTasks();
    if (solvedRegExpTasks.contains(task)) {
      return;
    }

    score += task.getScore();

    getSolvedRegExpTasks().add(task);
    RegExpLevel regExpLevel = task.getRegExpLevel();

    List<RegExpTask> enabledSolvedTask = getSolvedRegExpTasks()
      .stream()
      .filter(v -> v.isEnabled())
      .collect(Collectors.toList());

    List<RegExpTask> enabledLevelTask = regExpLevel
      .getRegExpTasks()
      .stream()
      .filter(v -> v.isEnabled())
      .collect(Collectors.toList());

    if (enabledLevelTask.size() == enabledSolvedTask.size()) {
      getSolvedRegExpLevels().add(regExpLevel);
    }
  }
}
