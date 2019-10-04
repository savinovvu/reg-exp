package ru.inbox.savinov_vu.core.aop.logging;


import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.core.env.Environment;

import java.util.Arrays;



@Aspect
@Slf4j
public class LoggingAspect {


  private final Environment env;


  public LoggingAspect(Environment env) {
    this.env = env;
  }


  @Pointcut("within(@org.springframework.stereotype.Component *)" +
    " || within(@org.springframework.stereotype.Controller *)" +
    " || within(@org.springframework.web.bind.annotation.RestController *)"
  )
  public void springBeanPointcut() {
    // Method is empty as this is just a Pointcut, the implementations are in the advices.
  }


  @AfterThrowing(pointcut = "springBeanPointcut()", throwing = "e")
  public void logAfterThrowing(JoinPoint joinPoint, Throwable e) {
    LOG.error("Exception in {}.{}() with cause = {}", joinPoint.getSignature().getDeclaringTypeName(),
      joinPoint.getSignature().getName(), e.getCause() != null ? e.getCause() : "NULL");
  }


  @Around("springBeanPointcut()")
  public Object logAround(ProceedingJoinPoint joinPoint) throws Throwable {
    if (LOG.isDebugEnabled()) {
      LOG.debug("Enter: {}.{}() with argument[s] = {}", joinPoint.getSignature().getDeclaringTypeName(),
        joinPoint.getSignature().getName(), Arrays.toString(joinPoint.getArgs()));
    }
    try {
      Object result = joinPoint.proceed();
      if (LOG.isDebugEnabled()) {
        LOG.debug("Exit: {}.{}() with result = {}", joinPoint.getSignature().getDeclaringTypeName(),
          joinPoint.getSignature().getName(), result);
      }
      return result;
    } catch (IllegalArgumentException e) {
      LOG.error("Illegal argument: {} in {}.{}()", Arrays.toString(joinPoint.getArgs()),
        joinPoint.getSignature().getDeclaringTypeName(), joinPoint.getSignature().getName());

      throw e;
    }
  }
}
