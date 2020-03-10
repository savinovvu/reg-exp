package ru.inbox.savinov_vu.core.security.jwt.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
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
  @SequenceGenerator(name = "logout_tokens_seq", sequenceName = "logout_tokens_seq", allocationSize = 1, initialValue = 1000)
  @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "logout_tokens_seq")
  private Integer id;

  private String token;

  private LocalDateTime expiration;

}
