package ru.inbox.savinov_vu.app.users.dto;

import lombok.Data;
import lombok.experimental.Accessors;

import javax.validation.constraints.Email;
import javax.validation.constraints.Size;



@Data
@Accessors(chain = true)
public class UserDto {

  private String id;

  @Size(min = 1, max = 255)
  private String firstName;

  @Size(min = 1, max = 255)
  private String lastName;

  @Size(min = 5, max = 255)
  private String login;

  @Email
  private String email;

  private String sex;

  private String birthDate;


}
