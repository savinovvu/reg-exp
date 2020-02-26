package ru.inbox.savinov_vu.app.users.dto;

import lombok.Data;
import lombok.experimental.Accessors;



@Data
@Accessors(chain = true)
public class UserDto {

  private String id;

  private String firstName;

  private String lastName;

  private String login;

  private String email;

  private String sex;


}
