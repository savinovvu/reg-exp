package ru.inbox.savinov_vu.app.tasks.level.model;

import com.fasterxml.jackson.annotation.JsonGetter;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;
import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;
import org.springframework.context.annotation.Lazy;
import ru.inbox.savinov_vu.app.tasks.task.model.RegExpTask;
import ru.inbox.savinov_vu.app.users.model.User;
import ru.inbox.savinov_vu.common.audit.BaseEntityAudit;
import ru.inbox.savinov_vu.common.interfaces.entityInterfaces.Identify;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.OrderBy;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Transient;
import java.util.List;



@Entity
@Getter
@Setter
@Accessors(chain = true)
@Table(name = "regexp_levels")
@EqualsAndHashCode(exclude = {"users", "regExpTasks"})
public class RegExpLevel extends BaseEntityAudit implements Identify {

  @Id
  @SequenceGenerator(name = "regexp_levels_seq", sequenceName = "regexp_levels_seq", allocationSize = 1, initialValue = 1000)
  @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "regexp_levels_seq")
  private Integer id;

  private Integer number;

  private String enDescription;

  private String ruDescription;

  private boolean enabled;

  @Transient
  private Boolean solve = false;

  @LazyCollection(LazyCollectionOption.TRUE)
  @OneToMany(cascade = CascadeType.ALL, mappedBy = "regExpLevel")
  @Lazy
  private List<RegExpTask> regExpTasks;

  @LazyCollection(LazyCollectionOption.TRUE)
  @ManyToMany(mappedBy = "solvedRegExpLevels")
  @OrderBy("name ASC")
  private List<User> users;


  @Transient
  @JsonGetter(value = "solve")
  public Boolean isSolve() {
    return solve;
  }


  // for Jackson serialization
  public Boolean getSolve() {
    return solve;
  }




}
