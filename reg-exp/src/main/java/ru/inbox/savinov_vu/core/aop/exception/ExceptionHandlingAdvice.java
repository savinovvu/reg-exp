package ru.inbox.savinov_vu.core.aop.exception;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

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


    private static String getStackTrace(final Throwable throwable) {
        final StringWriter sw = new StringWriter();
        final PrintWriter pw = new PrintWriter(sw, true);
        throwable.printStackTrace(pw);
        return sw.getBuffer().toString();
    }
}
