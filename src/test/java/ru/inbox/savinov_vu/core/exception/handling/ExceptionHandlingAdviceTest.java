package ru.inbox.savinov_vu.core.exception.handling;

import lombok.SneakyThrows;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.core.MethodParameter;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultMatcher;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.validation.BindException;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import ru.inbox.savinov_vu.core.exception.flow.InvalidParameterException;
import ru.inbox.savinov_vu.core.exception.flow.ValidationException;
import ru.inbox.savinov_vu.test_helpers.controller.ExceptionAdviceHelperController;

import java.util.stream.Stream;

import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(MockitoExtension.class)
class ExceptionHandlingAdviceTest {


  @Mock
  private ExceptionAdviceHelperController controller;

  private MockMvc mockMvc;


  @BeforeEach
  public void setUp() {
    this.mockMvc = MockMvcBuilders
      .standaloneSetup(controller)
      .setControllerAdvice(new ExceptionHandlingAdvice())
      .build();
  }

  @ParameterizedTest
  @MethodSource("getScenario")
  public void test(Exception exception, ResultMatcher statusMatcher, String expectedMessage) throws Exception {
    given(controller.make()).willAnswer((x) -> {
      throw exception;
    });

    mockMvc.perform(get("/error/invoke"))
      .andExpect(statusMatcher)
      .andExpect(jsonPath("$.status").value("error"))
      .andExpect(jsonPath("$.message").value(expectedMessage));
  }

  public static Stream<Arguments> getScenario() {
    return Stream.of(
      Arguments.of(new Exception("Unexpected Exception"), status().isInternalServerError(), "Internal Server Error"),
      Arguments.of(new InvalidParameterException("invalid param"), status().isNotFound(), "invalid param"),
      Arguments.of(new ValidationException("field is not valid", new RuntimeException()), status().isBadRequest(), "field is not valid")
    );


  }


  @SneakyThrows
  private static MethodParameter getParameter() {
    return MethodParameter
      .forParameter(
        ExceptionHandlingAdviceTest.class
          .getMethod("mockMethodForTest", Boolean.class).getParameters()[0]);
  }


  public void mockMethodForTest(Boolean param) {

  }

  @Test
  public void testMethodArgumentNotValidException() throws Exception {
    BindException bindingResult = new BindException("target", "name");
    bindingResult.addError(new ObjectError("objectName", "some message"));
    MethodArgumentNotValidException exception = new MethodArgumentNotValidException(getParameter(), bindingResult);
    String message = "some message";
    given(controller.make()).willAnswer((x) -> {
      throw exception;
    });

    mockMvc.perform(get("/error/invoke"))
      .andExpect(jsonPath("$[0]").value(message));
  }
}
