package ru.inbox.savinov_vu.app.users.model;

import lombok.Data;
import lombok.experimental.Accessors;

import static java.util.Objects.isNull;



@Data
@Accessors(chain = true)
public class UserDto {

  private String name;

  private String email;

  private String login;

  private String password;

  private String passwordRepeat;


  public static User dtoToEntity(UserDto userDto) {
    if (isNull(userDto)) {
      return null;
    }
    if (!userDto.getPassword().equals(userDto.getPasswordRepeat())) {
      return null;
    }

    User user = new User()
      .setFirstName(userDto.getName())
      .setEmail(userDto.getEmail())
      .setLogin(userDto.getLogin())
      .setPassword(userDto.getPassword());
    return user;
  }

}
