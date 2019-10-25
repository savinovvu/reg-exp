package ru.inbox.savinov_vu.app.tasks.level.repository;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import ru.inbox.savinov_vu.app.tasks.level.model.RegExpLevel;
import ru.inbox.savinov_vu.config.AbstractCommonConfiguration;

import javax.annotation.Resource;

import static org.junit.jupiter.api.Assertions.assertNull;
import static org.springframework.test.util.AssertionErrors.assertEquals;
import static ru.inbox.savinov_vu.stumb.RegExpLevelFactory.getRegExpLevel;



class RegExpLevelRepositoryTest extends AbstractCommonConfiguration {


  @Resource
  private RegExpLevelRepository repository;


  @BeforeEach
  public void initTest() {
    if (repository.findAll().isEmpty()) {
      RegExpLevel regExpLevel = getRegExpLevel();
      repository.saveAndFlush(regExpLevel);
    }
  }


  @Test
  public void createAndReadLevelTest() {
    RegExpLevel result = repository.findById(1000).orElse(new RegExpLevel());
    assertEquals("RegExpLevel must have id", result.getId().intValue(), 1000);
  }


  @Test
  public void updateLevelTest() {
    RegExpLevel regExpLevel = repository.findById(1000).orElse(new RegExpLevel());
    regExpLevel.setNumber(5);
    RegExpLevel result = repository.saveAndFlush(regExpLevel);
    assertEquals("RegExpLevel must have id", result.getNumber().intValue(), 5);
  }


  @Test
  public void deleteLevelTest() {
    RegExpLevel regExpLevel = getRegExpLevel();
    RegExpLevel result = repository.saveAndFlush(regExpLevel);
    repository.delete(result);
    RegExpLevel nullLevel = repository.findById(result.getId()).orElse(null);
    assertNull(nullLevel,"RegExpLevel must be null");
  }


  @Test
  public void getByNumberTest() {
    RegExpLevel regExpLevel = getRegExpLevel();
    regExpLevel.setNumber(2);
    RegExpLevel savedLevel = repository.saveAndFlush(regExpLevel);
    RegExpLevel byNumberLevel = repository.findByNumber(savedLevel.getNumber());
    assertEquals("RegExpLevel find by number not correct", byNumberLevel.getId(), savedLevel.getId());
  }

}
