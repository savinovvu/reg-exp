package ru.inbox.savinov_vu.controller.exception;

import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;



@RestControllerAdvice
public class ExceptionHandlingAdvice {

    @ExceptionHandler
    public String handleDefault(Exception e) {
        var result = e + " " + e.getStackTrace()[0].toString();
        return result;
    }
}
