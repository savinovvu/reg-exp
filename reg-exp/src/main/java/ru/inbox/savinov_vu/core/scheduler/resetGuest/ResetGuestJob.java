package ru.inbox.savinov_vu.core.scheduler.resetGuest;

import lombok.RequiredArgsConstructor;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;



@Service
@RequiredArgsConstructor
public class ResetGuestJob {


  @Resource
  private final ResetGuestService resetGuestService;


  @Scheduled(cron = "${scheduler.cron.guest-reset}")
  public void dataGenerator() {
    resetGuestService.resetGuest();
  }


}
