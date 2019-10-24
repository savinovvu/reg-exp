package ru.inbox.savinov_vu.app.tasks.level.model;

import com.fasterxml.jackson.annotation.JsonGetter;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;
import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;
import org.hibernate.proxy.HibernateProxyHelper;
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
import java.util.Objects;

import static java.util.Objects.isNull;
import static java.util.Objects.nonNull;



@Entity
@Getter
@Setter
@Accessors(chain = true)
@Table(name = "regexp_levels")
public class RegExpLevel extends BaseEntityAudit implements Identify {

  @Id
  @SequenceGenerator(name = "GLOBAL_SEQ", sequenceName = "GLOBAL_SEQ", allocationSize = 1, initialValue = 1000)
  @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "GLOBAL_SEQ")
  private Integer id;

  private Integer number;

  private String enDescription;

  private String ruDescription;

  @LazyCollection(LazyCollectionOption.TRUE)
  @OneToMany(cascade = CascadeType.ALL, mappedBy = "regExpLevel")
  @Lazy
  private List<RegExpTask> regExpTasks;

  @LazyCollection(LazyCollectionOption.TRUE)
  @ManyToMany(mappedBy = "solvedRegExpLevels")
  @OrderBy("name ASC")
  private List<User> users;

  private boolean enabled;

  @Transient
  private Boolean solve = false;


  @Transient
  @JsonGetter(value = "solve")
  public Boolean isSolve() {
    return solve;
  }


  // for Jackson serialization
  public Boolean getSolve() {
    return solve;
  }


  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || getClass() != HibernateProxyHelper.getClassWithoutInitializingProxy(o)) return false;
    RegExpLevel that = (RegExpLevel) o;
    if (isNull(id) && isNull(that.id)) return true;
    if (isNull(id) && nonNull(that.id)) return false;
    if (nonNull(id) && isNull(that.id)) return false;
    return id.equals(that.id);
  }


  @Override
  public int hashCode() {
    return Objects.hash(id);
  }
}
