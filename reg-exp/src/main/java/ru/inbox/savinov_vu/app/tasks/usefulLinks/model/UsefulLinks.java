package ru.inbox.savinov_vu.app.tasks.usefulLinks.model;

import lombok.Data;
import lombok.experimental.Accessors;
import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;
import ru.inbox.savinov_vu.app.tasks.task.model.RegExpTask;
import ru.inbox.savinov_vu.common.interfaces.entityInterfaces.Identify;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.SequenceGenerator;
import java.util.List;



@Entity
@Data
@Accessors(chain = true)
public class UsefulLinks implements Identify {

  @Id
  @SequenceGenerator(name = "GLOBAL_SEQ", sequenceName = "GLOBAL_SEQ", allocationSize = 1, initialValue = 1000)
  @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "GLOBAL_SEQ")
  private Integer id;

  private String link;

  private String ruDescription;

  private String enDescription;

  @LazyCollection(LazyCollectionOption.TRUE)
  @ManyToMany
  @JoinTable(name = "useful_links_regexp_task",
    joinColumns = {
      @JoinColumn(name = "useful_link_id", referencedColumnName = "id")},
    inverseJoinColumns = {
      @JoinColumn(name = "regexp_task_id", referencedColumnName = "id")})
  private List<RegExpTask> regExpTasks;


}
