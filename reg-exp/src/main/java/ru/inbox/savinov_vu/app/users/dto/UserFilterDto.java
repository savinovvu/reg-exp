package ru.inbox.savinov_vu.app.users.dto;

import lombok.Data;



@Data
public class UserFilterDto {

  private Integer id;

  private String firstName;

  private String lastName;

  private String email;

  private String login;

  private String sex;

  private String birthDate;

  private Boolean enabled;

  private int size = 30;

  private int page = 1;


}
