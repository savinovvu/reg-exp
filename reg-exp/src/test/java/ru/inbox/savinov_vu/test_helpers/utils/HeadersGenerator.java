package ru.inbox.savinov_vu.test_helpers.utils;

import org.springframework.http.HttpHeaders;
import org.springframework.stereotype.Component;
import ru.inbox.savinov_vu.app.users.model.User_;
import ru.inbox.savinov_vu.core.security.jwt.config.JwtHelper;

import javax.annotation.Resource;



@Component
public class HeadersGenerator {

  @Resource
  JwtHelper jwtHelper;


  public HttpHeaders generateHeaders(Integer userId) {
    String token = jwtHelper.generateToken("test");
    HttpHeaders headers = new HttpHeaders();
    headers.setBearerAuth(token);
    headers.set(User_.ID, String.valueOf(userId));
    return headers;
  }

}
