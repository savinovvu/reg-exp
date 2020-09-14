package ru.inbox.savinov_vu.core.scheduler.deleteExpireLogoutToken;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.inbox.savinov_vu.core.security.repository.LogoutTokenRepository;

import javax.annotation.Resource;
import java.time.LocalDateTime;



@Service
@RequiredArgsConstructor
public class DeleteExpireLogoutTokenJobService {

  @Resource
  private final LogoutTokenRepository logoutTokenRepository;


  @Transactional
  public void deleteExpired() {
    logoutTokenRepository.deleteByExpirationBefore(LocalDateTime.now());
  }

}
