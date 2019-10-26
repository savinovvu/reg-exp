package ru.inbox.savinov_vu.app.tasks.level.model;

import lombok.Data;



@Data
public class SomeTo {

  private Integer id;

  private String name;


  public SomeTo(Integer id, String name) {
    this.id = id;
    this.name = name;
  }


  public SomeTo() {
  }
}
