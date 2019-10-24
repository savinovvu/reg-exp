package ru.inbox.savinov_vu.common.audit;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.Column;
import javax.persistence.EntityListeners;
import javax.persistence.MappedSuperclass;
import java.time.LocalDateTime;



@Data
@MappedSuperclass
@NoArgsConstructor
@EntityListeners({AuditingEntityListener.class})
@Accessors(chain = true)
public abstract class BaseEntityAudit {

  @Column(name = "updated_at")
  @JsonProperty("updatedAt")
  @LastModifiedDate
  private LocalDateTime updatedAt;

  @Column(name = "created_at")
  @JsonProperty("createdAt")
  @CreatedDate
  private LocalDateTime createdAt;


}
