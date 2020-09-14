package ru.inbox.savinov_vu.core.exception;

import ru.inbox.savinov_vu.core.exception.base.AppException;



public class AuthenticationException extends AppException {

  private static final long serialVersionUID = 9125254351678473266L;


  public AuthenticationException(String message) {
    super(message);
  }

}
