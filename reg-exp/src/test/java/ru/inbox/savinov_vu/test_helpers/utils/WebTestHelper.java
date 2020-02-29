package ru.inbox.savinov_vu.test_helpers.utils;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.stereotype.Component;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import ru.inbox.savinov_vu.app.users.model.User;
import ru.inbox.savinov_vu.test_helpers.data.factories.user.UserFactory;

import javax.annotation.Resource;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;



@Component
public class WebTestHelper {

  @Resource
  private MockMvc mockMvc;

  @Resource
  private ObjectMapper objectMapper;

  private static User basicUser = UserFactory.getWithId(1);


  public String performRequest(MockHttpServletRequestBuilder builder, Object body) {
    try {
      String json = objectMapper.writeValueAsString(body);
      String result = performRequest(builder
        .contentType(MediaType.APPLICATION_JSON_UTF8)
        .content(json));

      return result;
    } catch (Exception e) {
      throw new RuntimeException(e);
    }
  }


  public String performRequest(MockHttpServletRequestBuilder builder) {

    try {
      MvcResult mvcResult = mockMvc.perform(builder)
        .andExpect(status().isOk())
        .andReturn();
      MockHttpServletResponse response = mvcResult.getResponse();
      return response.getContentAsString();

    } catch (Exception e) {
      throw new RuntimeException(e);
    }
  }


  public String objectToJson(Object object) {
    try {
      String result = objectMapper.writeValueAsString(object);
      return result;
    } catch (Exception e) {
      throw new RuntimeException(e);
    }
  }

}
