package ru.inbox.savinov_vu.test_helpers.data.init;

import org.springframework.stereotype.Service;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import ru.inbox.savinov_vu.app.users.model.User;
import ru.inbox.savinov_vu.core.security.jwt.config.JwtHelper;

import javax.annotation.Resource;



@Service
public class MockMvcBuilderInitializer {

  @Resource
  private JwtHelper jwtHelper;


  public MockHttpServletRequestBuilder getRequest(String url, User user) {
    String token = jwtHelper.generateToken(user.getLogin());
    return MockMvcRequestBuilders.get(url)
      .header("Authorization", "Bearer " + token)
      .header("id", String.valueOf(user.getId()));
  }


  public MockHttpServletRequestBuilder putRequest(String url, User user) {
    String token = jwtHelper.generateToken(user.getLogin());
    return MockMvcRequestBuilders.put(url)
      .header("Authorization", "Bearer " + token)
      .header("id", String.valueOf(user.getId()));
  }


  public MockHttpServletRequestBuilder deleteRequest(String url, User user) {
    String token = jwtHelper.generateToken(user.getLogin());
    return MockMvcRequestBuilders.delete(url)
      .header("Authorization", "Bearer " + token)
      .header("id", String.valueOf(user.getId()));
  }
}
