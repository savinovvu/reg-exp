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



@RestControllerAdvice
@Slf4j
public class ExceptionHandlingAdvice {


  @ExceptionHandler
  public ResponseEntity handleDefault(Exception e) {
    LOG.debug(e.getMessage());
    return new ResponseEntity(HttpStatus.INTERNAL_SERVER_ERROR);
  }


  @ExceptionHandler(InvalidParameterException.class)
  public ResponseEntity handleDefault(InvalidParameterException e) {
    LOG.debug(e.getMessage());
    return new ResponseEntity(HttpStatus.NOT_FOUND);
  }


  @ExceptionHandler(ValidationException.class)
  public ResponseEntity handleDefault(ValidationException e) {
    LOG.debug(e.getMessage());
    return new ResponseEntity(HttpStatus.BAD_REQUEST);
  }


  @ExceptionHandler(MethodArgumentNotValidException.class)
  public ResponseEntity handleDefault(MethodArgumentNotValidException e) {
    LOG.debug(e.getMessage());
    return new ResponseEntity(HttpStatus.BAD_REQUEST);
  }


  @ExceptionHandler(AuthenticationException.class)
  public ResponseEntity handleDefault(AuthenticationException e) {
    LOG.debug(e.getMessage());
    return new ResponseEntity(HttpStatus.UNAUTHORIZED);
  }


}
