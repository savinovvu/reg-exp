package ru.inbox.savinov_vu.core.security.jwt.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.time.LocalDateTime;



@Entity
@Table(name = "logout_tokens")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Accessors(chain = true)
public class LogoutToken {

  @Id
  private Integer id;

  private String token;

  private LocalDateTime expiration;

}
