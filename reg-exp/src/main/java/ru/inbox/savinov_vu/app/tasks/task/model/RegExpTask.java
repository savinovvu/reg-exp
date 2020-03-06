package ru.inbox.savinov_vu.app.tasks.task.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import lombok.experimental.Accessors;
import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;
import ru.inbox.savinov_vu.app.tasks.comment.model.Comment;
import ru.inbox.savinov_vu.app.tasks.level.model.RegExpLevel;
import ru.inbox.savinov_vu.app.tasks.like.model.Like;
import ru.inbox.savinov_vu.app.tasks.usefulLinks.model.UsefulLinks;
import ru.inbox.savinov_vu.app.users.model.User;
import ru.inbox.savinov_vu.common.audit.BaseEntityAudit;
import ru.inbox.savinov_vu.common.interfaces.entityInterfaces.Identify;
import ru.inbox.savinov_vu.core.converter.ListStringConverter;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Convert;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import java.util.ArrayList;
import java.util.List;



@Entity
@Table(name = "regexp_tasks")
@Data
@Accessors(chain = true)
public class RegExpTask extends BaseEntityAudit implements Identify {

  @Id
  @SequenceGenerator(name = "regexp_task_seq", sequenceName = "regexp_task_seq", allocationSize = 1, initialValue = 1000)
  @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "regexp_task_seq")
  private Integer id;

  private Integer number;

  private String ruTitle;

  private String enTitle;

  private String ruDescription;

  private String enDescription;

  @Column(name = "matched_strings", columnDefinition = "text")
  @Convert(converter = ListStringConverter.class)
  private List<String> matchedStrings;

  @Column(name = "excluded_strings", columnDefinition = "text")
  @Convert(converter = ListStringConverter.class)
  private List<String> excludedStrings;

  @Column(name = "required_substrings", columnDefinition = "text")
  @Convert(converter = ListStringConverter.class)
  private List<String> requiredSubStrings;

  @Column(name = "excluded_answers", columnDefinition = "text")
  @Convert(converter = ListStringConverter.class)
  private List<String> excludedAnswers;

  @LazyCollection(LazyCollectionOption.TRUE)
  @OneToMany(cascade = CascadeType.REMOVE, mappedBy = "regExpTask")
  private List<Like> likes;

  @LazyCollection(LazyCollectionOption.TRUE)
  @OneToMany(cascade = CascadeType.REMOVE, mappedBy = "regExpTask")
  private List<Comment> comments;

  @ManyToOne
  @JoinColumn(name = "regexp_level_id")
  private RegExpLevel regExpLevel;

  @ManyToOne
  @JoinColumn(name = "author_id", nullable = false)
  private User author;

  @LazyCollection(LazyCollectionOption.TRUE)
  @ManyToMany(mappedBy = "solvedRegExpTasks")
  private List<User> users;

  @Column(name = "answers", columnDefinition = "text")
  @Convert(converter = ListStringConverter.class)
  private List<String> answers;

  @JsonIgnore
  private boolean enabled;

  @LazyCollection(LazyCollectionOption.FALSE)
  @ManyToMany(mappedBy = "regExpTasks")
  private List<UsefulLinks> usefulLinks;


  public RegExpTask() {
    matchedStrings = new ArrayList<>();
    excludedStrings = new ArrayList<>();
    requiredSubStrings = new ArrayList<>();
    excludedAnswers = new ArrayList<>();
  }
}
