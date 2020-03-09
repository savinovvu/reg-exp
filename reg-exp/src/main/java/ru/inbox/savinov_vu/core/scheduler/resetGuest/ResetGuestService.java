package ru.inbox.savinov_vu.core.scheduler.resetGuest;

import lombok.RequiredArgsConstructor;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.inbox.savinov_vu.app.users.model.User;
import ru.inbox.savinov_vu.app.users.service.UserService;

import javax.annotation.Resource;

import static ru.inbox.savinov_vu.common.constant.StringConstants.GUEST_LOGIN;



@Service
@RequiredArgsConstructor
public class ResetGuestService {

  @Resource
  private final JdbcTemplate jdbcTemplate;

  @Resource
  private final UserService userService;


  @Transactional
  public void resetGuest() {
    User user = userService.getByLogin(GUEST_LOGIN);
    jdbcTemplate.execute("DELETE FROM solved_regexp_tasks where user_id=" + user.getId());
    jdbcTemplate.execute("DELETE FROM solved_regexp_levels where user_id=" + user.getId());
  }


}
