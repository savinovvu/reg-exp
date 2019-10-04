package ru.inbox.savinov_vu.app.tasks.taskAnswer.model;

import lombok.Data;
import lombok.experimental.Accessors;
import ru.inbox.savinov_vu.app.tasks.task.model.RegExpTask;
import ru.inbox.savinov_vu.common.interfaces.entityInterfaces.Identify;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;



@Entity
@Table(uniqueConstraints = {@UniqueConstraint(columnNames = {"regExpTask_id", "answer"})})
@Data
@Accessors(chain = true)
public class RegExpTaskAnswer implements Identify {


  @Id
  @SequenceGenerator(name = "GLOBAL_SEQ", sequenceName = "GLOBAL_SEQ", allocationSize = 1, initialValue = 1000)
  @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "GLOBAL_SEQ")
  private Integer id;

  private String answer;

  @ManyToOne
  @JoinColumn(name = "regExpTask_id", nullable = false)
  private RegExpTask regExpTask;


}
