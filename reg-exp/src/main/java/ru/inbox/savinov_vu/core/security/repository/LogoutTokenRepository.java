package ru.inbox.savinov_vu.core.security.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.inbox.savinov_vu.core.security.jwt.model.LogoutToken;

import java.util.Optional;



@Repository
public interface LogoutTokenRepository extends JpaRepository<LogoutToken, Integer> {

  Optional<LogoutToken> findByToken(String token);

}
