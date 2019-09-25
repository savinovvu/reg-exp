package ru.inbox.savinov_vu.core.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;



@RestControllerAdvice
public class ExceptionHandlingAdvice {

    @ExceptionHandler
    public ResponseEntity handleDefault(Exception e) {
        var result = e + " " + e.getStackTrace()[0].toString();
        return new ResponseEntity(result, HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
