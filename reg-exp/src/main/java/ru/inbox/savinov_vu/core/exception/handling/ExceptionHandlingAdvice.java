package ru.inbox.savinov_vu.core.exception.handling;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import ru.inbox.savinov_vu.core.exception.AuthenticationException;
import ru.inbox.savinov_vu.core.exception.flow.InvalidParameterException;
import ru.inbox.savinov_vu.core.exception.flow.ValidationException;

import java.io.PrintWriter;
import java.io.StringWriter;



@RestControllerAdvice
@Slf4j
public class ExceptionHandlingAdvice {


  @ExceptionHandler
  public ResponseEntity handleDefault(Exception e) {
    LOG.debug(getStackTrace(e));
    return new ResponseEntity(getStackTrace(e), HttpStatus.INTERNAL_SERVER_ERROR);
  }


  @ExceptionHandler(InvalidParameterException.class)
  public ResponseEntity handleDefault(InvalidParameterException e) {
    LOG.debug(getStackTrace(e));
    return new ResponseEntity(getStackTrace(e), HttpStatus.NOT_FOUND);
  }


  @ExceptionHandler(ValidationException.class)
  public ResponseEntity handleDefault(ValidationException e) {
    LOG.debug(getStackTrace(e));
    return new ResponseEntity(getStackTrace(e), HttpStatus.BAD_REQUEST);
  }


  @ExceptionHandler(MethodArgumentNotValidException.class)
  public ResponseEntity handleDefault(MethodArgumentNotValidException e) {
    LOG.debug(getStackTrace(e));
    return new ResponseEntity(getStackTrace(e), HttpStatus.BAD_REQUEST);
  }


  @ExceptionHandler(AuthenticationException.class)
  public ResponseEntity handleDefault(AuthenticationException e) {
    LOG.debug(getStackTrace(e));
    return new ResponseEntity(getStackTrace(e), HttpStatus.UNAUTHORIZED);
  }


  private static String getStackTrace(final Throwable throwable) {
    final StringWriter sw = new StringWriter();
    final PrintWriter pw = new PrintWriter(sw, true);
    throwable.printStackTrace(pw);
    return sw.getBuffer().toString();
  }
}
