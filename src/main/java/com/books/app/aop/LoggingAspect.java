package com.books.app.aop;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class LoggingAspect {
    private static final Logger logger = LoggerFactory.getLogger(LoggingAspect.class);

    @Around("execution(public * com.books.app.controller..*(..))")
    public Object controllerLogging(ProceedingJoinPoint joinPoint) throws Throwable {
        logger.info("Invoked {} from {}", joinPoint.getSignature().getName(),
                joinPoint.getTarget().getClass().getSimpleName());
        long startTime = System.currentTimeMillis();
        Object result = joinPoint.proceed();
        long endTime = System.currentTimeMillis();
        long duration = endTime - startTime;
        logger.info("{} seconds.", ((float) duration / 1000.0));
        return result;
    }
}
