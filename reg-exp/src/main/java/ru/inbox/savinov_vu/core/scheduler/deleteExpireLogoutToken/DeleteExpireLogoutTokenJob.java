package ru.inbox.savinov_vu.core.scheduler.deleteExpireLogoutToken;

import lombok.RequiredArgsConstructor;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;



@Service
@RequiredArgsConstructor
public class DeleteExpireLogoutTokenJob {

  @Resource
  private final DeleteExpireLogoutTokenJobService service;


  @Scheduled(cron = "${scheduler.cron.expire-logout-token-delete}")
  public void invoke() {
    service.deleteExpired();
  }

}
