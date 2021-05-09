package ru.inbox.savinov_vu.app.tasks.level.repository;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import ru.inbox.savinov_vu.app.tasks.level.model.RegExpLevel;
import ru.inbox.savinov_vu.config.AbstractJpaRepositoryTest;

import javax.annotation.Resource;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static ru.inbox.savinov_vu.test_helpers.data.factories.RegExpLevelFactory.getRegExpLevel;


class RegExpLevelRepositoryTest extends AbstractJpaRepositoryTest<RegExpLevel> {

  @Resource
  private RegExpLevelRepository subject;

  private RegExpLevel level;

  @BeforeEach
  public void initTest() {
    level = getRegExpLevel();
  }

  @Test
  public void findById() {
    Integer id = (Integer) em.persistAndGetId(level);
    em.flush();
    em.clear();
    assertTrue(subject.findById(id).isPresent());
  }

  @Test
  public void findAll() {
    em.persist(getRegExpLevel());
    em.persist(getRegExpLevel());
    em.persist(getRegExpLevel());
    em.flush();
    em.clear();
    List<RegExpLevel> all = subject.findAll();
    assertEquals(3, all.size());
  }

  @Test
  public void update() {
    em.persistAndFlush(level);
    em.clear();
    level.setScore(80);
    RegExpLevel actual = subject.saveAndFlush(level);
    assertEquals(80, actual.getScore());
  }

  @Test
  public void delete() {
    Integer id = (Integer) em.persistAndGetId(level);
    em.flush();
    em.clear();
    subject.deleteById(id);
    RegExpLevel actual = em.find(RegExpLevel.class, id);
    assertNull(actual);
  }

  @Test
  public void create() {
    RegExpLevel regExpLevel = subject.saveAndFlush(level);
    RegExpLevel actual = em.find(RegExpLevel.class, regExpLevel.getId());
    assertNotNull(actual);
  }

}
