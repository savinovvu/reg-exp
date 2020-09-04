package ru.inbox.savinov_vu.core.exception.handling;

import io.micrometer.core.instrument.util.StringEscapeUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import ru.inbox.savinov_vu.core.exception.AuthenticationException;
import ru.inbox.savinov_vu.core.exception.flow.InvalidParameterException;
import ru.inbox.savinov_vu.core.exception.flow.ValidationException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;
import java.util.Map;

import static org.springframework.http.HttpStatus.*;


@RestControllerAdvice
@Slf4j
public class ExceptionHandlingAdvice {


  private static final HttpStatus DEFAULT_HTTP_STATUS = HttpStatus.INTERNAL_SERVER_ERROR;
  private static final String LOG_TEMPLATE = "request to {}; {}: {}, responding with <{} {}>";
  private static final String PROCESSED_EXCEPTION_MESSAGE_TEMPLATE = "{\"status\":\"error\",\"message\":\"%s\"}";
  private static final String UNPROCESSED_EXCEPTION_MESSAGE_TEMPLATE = "{\"status\":\"error\",\"message\":\"Internal Server Error\"}";

  private Map<Class<?>, HttpStatus> exceptionStatusMap = new HashMap<>();


  public ExceptionHandlingAdvice(){
    exceptionStatusMap.put(InvalidParameterException.class, NOT_FOUND);
    exceptionStatusMap.put(ValidationException.class, BAD_REQUEST);
    exceptionStatusMap.put(MethodArgumentNotValidException.class, BAD_REQUEST);
    exceptionStatusMap.put(AuthenticationException.class, UNAUTHORIZED);
  }

  @ExceptionHandler
  public ResponseEntity<String> handleDefault(Throwable t, HttpServletRequest req, HttpServletResponse resp) {
    HttpStatus status = exceptionStatusMap.getOrDefault(t.getClass(), DEFAULT_HTTP_STATUS);

    LOG.warn(LOG_TEMPLATE,
      req.getRequestURL(),
      t.getClass().getCanonicalName(),
      t.getMessage(),
      status.value(),
      status.getReasonPhrase(),
      t);
    return new ResponseEntity<>(getMessage(t), status);
  }

  private String getMessage(Throwable t) {
    if (exceptionStatusMap.containsKey(t.getClass())) {
      String message = StringEscapeUtils.escapeJson(t.getMessage());
      return String.format(PROCESSED_EXCEPTION_MESSAGE_TEMPLATE, message);
    }
    return UNPROCESSED_EXCEPTION_MESSAGE_TEMPLATE;
  }

}
