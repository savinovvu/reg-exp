package ru.inbox.savinov_vu.app.tasks.comment.model;


import lombok.Data;
import lombok.experimental.Accessors;
import ru.inbox.savinov_vu.app.tasks.task.model.RegExpTask;
import ru.inbox.savinov_vu.app.users.model.User;
import ru.inbox.savinov_vu.common.interfaces.entityInterfaces.Identify;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;



@Entity
@Data
@Accessors(chain = true)
@Table(name = "comments")
public class Comment implements Identify {

  @Id
  @SequenceGenerator(name = "comments_seq", sequenceName = "comments_seq", allocationSize = 1, initialValue = 1000)
  @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "comments_seq")
  private Integer id;

  private String text;

  @ManyToOne
  @JoinColumn(name = "user_id", nullable = false)
  private User user;

  @ManyToOne
  @JoinColumn(name = "regexp_task_id", nullable = false)
  private RegExpTask regExpTask;


}
