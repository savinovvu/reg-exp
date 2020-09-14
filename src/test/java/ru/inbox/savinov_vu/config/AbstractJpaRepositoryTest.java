package ru.inbox.savinov_vu.config;

import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.liquibase.LiquibaseAutoConfiguration;
import org.springframework.boot.jdbc.EmbeddedDatabaseConnection;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import javax.annotation.Resource;

@ExtendWith(SpringExtension.class)
@DataJpaTest(showSql = false)
@AutoConfigureTestDatabase(connection = EmbeddedDatabaseConnection.H2)
@EnableAutoConfiguration(exclude = LiquibaseAutoConfiguration.class)
public class AbstractJpaRepositoryTest<T> {

  @Resource
  protected TestEntityManager em;

  public T reload(T entity) {
    em.merge(entity);
    return em.refresh(entity);
  }
}
