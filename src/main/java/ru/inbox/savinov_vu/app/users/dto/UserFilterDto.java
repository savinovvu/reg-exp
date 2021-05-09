package ru.inbox.savinov_vu.app.users.dto;

import lombok.Data;
import lombok.experimental.Accessors;


@Data
@Accessors(chain = true)
public class UserFilterDto {

  private String id;

  private String firstName;

  private String lastName;

  private String email;

  private String login;

  private String sex;

  private String birthDate;

  private Boolean enabled;

  private String score;

  private int size = 2;

  private int page = 1;

  private String sort;

  private String direction;


}
