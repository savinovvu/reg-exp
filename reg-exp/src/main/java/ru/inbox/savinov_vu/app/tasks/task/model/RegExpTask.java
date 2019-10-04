package ru.inbox.savinov_vu.app.tasks.task.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import lombok.experimental.Accessors;
import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;
import ru.inbox.savinov_vu.app.tasks.comment.model.Comment;
import ru.inbox.savinov_vu.app.tasks.level.model.RegExpLevel;
import ru.inbox.savinov_vu.app.tasks.like.model.Like;
import ru.inbox.savinov_vu.app.tasks.taskAnswer.model.RegExpTaskAnswer;
import ru.inbox.savinov_vu.app.tasks.usefulLinks.model.UsefulLinks;
import ru.inbox.savinov_vu.app.users.model.User;
import ru.inbox.savinov_vu.common.interfaces.entityInterfaces.Identify;

import javax.persistence.CascadeType;
import javax.persistence.CollectionTable;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Transient;
import javax.persistence.UniqueConstraint;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;



@Entity
@Table(uniqueConstraints = {@UniqueConstraint(columnNames = {"regexplevel_id", "number"})})
@Data
@Accessors(chain = true)
public class RegExpTask implements Identify {

  @Id
  @SequenceGenerator(name = "GLOBAL_SEQ", sequenceName = "GLOBAL_SEQ", allocationSize = 1, initialValue = 1000)
  @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "GLOBAL_SEQ")
  private Integer id;

  private Integer number;

  private String name;

  private String description;

  @ElementCollection(fetch = FetchType.EAGER)
  @CollectionTable(joinColumns = @JoinColumn(name = "regexptask_id"))
  private Set<String> matchStrings = new HashSet<>();

  @ElementCollection(fetch = FetchType.EAGER)
  @CollectionTable(joinColumns = @JoinColumn(name = "regexptask_id"))
  private Set<String> excludedStrings = new HashSet<>();

  @ElementCollection(fetch = FetchType.EAGER)
  @CollectionTable(joinColumns = @JoinColumn(name = "regexptask_id"))
  private Set<String> requiredSubStrings = new HashSet<>();

  @ElementCollection(fetch = FetchType.EAGER)
  @CollectionTable(joinColumns = @JoinColumn(name = "regexptask_id"))
  private Set<String> excludedAnswers = new HashSet<>();

  @LazyCollection(LazyCollectionOption.TRUE)
  @OneToMany(cascade = CascadeType.REMOVE, mappedBy = "regExpTask")
  private List<Like> likes;

  @LazyCollection(LazyCollectionOption.TRUE)
  @OneToMany(cascade = CascadeType.REMOVE, mappedBy = "regExpTask")
  private List<Comment> comments;

  @ManyToOne
  @JoinColumn(name = "regexplevel_id", nullable = true)
  private RegExpLevel regExpLevel;

  @ManyToOne
  @JoinColumn(name = "author_id", nullable = true)
  private User author;

  @LazyCollection(LazyCollectionOption.TRUE)
  @ManyToMany(mappedBy = "solvedRegExpTasks")
  private List<User> users;

  @LazyCollection(LazyCollectionOption.TRUE)
  @OneToMany(cascade = CascadeType.REMOVE, mappedBy = "regExpTask")
  private List<RegExpTaskAnswer> answers = new ArrayList<>();

  @JsonIgnore
  private Boolean enabled;

  @LazyCollection(LazyCollectionOption.FALSE)
  @ManyToMany(mappedBy = "regExpTasks")
  private List<UsefulLinks> usefulLinks;

  @Transient
  @JsonProperty("solve")
  private boolean solve;

}
