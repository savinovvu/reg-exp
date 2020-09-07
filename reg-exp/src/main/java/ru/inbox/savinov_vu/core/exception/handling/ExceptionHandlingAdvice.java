package ru.inbox.savinov_vu.core.exception.handling;

import com.fasterxml.jackson.databind.ObjectMapper;
import io.micrometer.core.instrument.util.StringEscapeUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import ru.inbox.savinov_vu.core.exception.AuthenticationException;
import ru.inbox.savinov_vu.core.exception.flow.InvalidParameterException;
import ru.inbox.savinov_vu.core.exception.flow.ValidationException;

import javax.servlet.http.HttpServletRequest;
import java.net.URI;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import static org.springframework.http.HttpStatus.*;


@RestControllerAdvice
@Slf4j
public class ExceptionHandlingAdvice {

  private final ObjectMapper mapper;

  private static final String LOGIN_PATH = "/login";
  private static final HttpStatus DEFAULT_HTTP_STATUS = HttpStatus.INTERNAL_SERVER_ERROR;
  private static final String LOG_TEMPLATE = "request to {}; {}: {}, responding with <{} {}>";
  private static final String PROCESSED_EXCEPTION_MESSAGE_TEMPLATE = "{\"status\":\"error\",\"message\":\"%s\"}";
  private static final String UNPROCESSED_EXCEPTION_MESSAGE_TEMPLATE = "{\"status\":\"error\",\"message\":\"Internal Server Error\"}";

  private final Map<Class<?>, HttpStatus> exceptionStatusMap = new HashMap<>();

  public ExceptionHandlingAdvice(ObjectMapper mapper) {
    this.mapper = mapper;
    exceptionStatusMap.put(InvalidParameterException.class, NOT_FOUND);
    exceptionStatusMap.put(ValidationException.class, BAD_REQUEST);
    exceptionStatusMap.put(MethodArgumentNotValidException.class, BAD_REQUEST);
    exceptionStatusMap.put(AuthenticationException.class, MOVED_PERMANENTLY);
  }

  @ExceptionHandler
  public ResponseEntity<String> handleDefault(Throwable t, HttpServletRequest req) {
    HttpStatus status = getStatus(t);
    handleLog(t, req, status);
    return new ResponseEntity<>(getMessage(t), status);
  }


  @ExceptionHandler(MethodArgumentNotValidException.class)
  public ResponseEntity<List<String>> handleNotValid(MethodArgumentNotValidException t,
                                                     HttpServletRequest req
  ) {
    HttpStatus status = getStatus(t);
    handleLog(t, req, status);
    HttpHeaders headers = new HttpHeaders();
    headers.setLocation(URI.create(LOGIN_PATH));
    List<String> messageList = t.getBindingResult().getAllErrors().stream().map(e -> e.getDefaultMessage()).collect(Collectors.toList());
    return new ResponseEntity<>(messageList, HttpStatus.BAD_REQUEST);
  }

  @ExceptionHandler(AuthenticationException.class)
  public ResponseEntity<String> handleAuthentication(AuthenticationException t, HttpServletRequest req) {
    HttpStatus status = getStatus(t);
    handleLog(t, req, status);
    HttpHeaders headers = new HttpHeaders();
    headers.setLocation(URI.create(LOGIN_PATH));
    return new ResponseEntity<>(headers, status);
  }

  private String getMessage(Throwable t) {
    if (exceptionStatusMap.containsKey(t.getClass())) {
      String message = StringEscapeUtils.escapeJson(t.getMessage());
      return String.format(PROCESSED_EXCEPTION_MESSAGE_TEMPLATE, message);
    }
    return UNPROCESSED_EXCEPTION_MESSAGE_TEMPLATE;
  }

  private void handleLog(Throwable t, HttpServletRequest req, HttpStatus status) {
    LOG.warn(LOG_TEMPLATE,
      req.getRequestURL(),
      t.getClass().getCanonicalName(),
      t.getMessage(),
      status.value(),
      status.getReasonPhrase(),
      t);
  }

  private HttpStatus getStatus(Throwable t) {
    return exceptionStatusMap.getOrDefault(t.getClass(), DEFAULT_HTTP_STATUS);
  }
}
