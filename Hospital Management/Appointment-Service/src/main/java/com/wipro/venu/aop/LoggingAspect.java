package com.wipro.venu.aop;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.ProceedingJoinPoint;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class LoggingAspect {

    private static final Logger log = LoggerFactory.getLogger(LoggingAspect.class);

    // Before method execution
    @Before("execution(* com.wipro.venu.service.impl.*.*(..))")
    public void logBefore(JoinPoint joinPoint) {
        log.info("Executing method: {} with arguments: {}",
                joinPoint.getSignature().toShortString(),
                joinPoint.getArgs());
    }

    // After successful execution
    @AfterReturning(pointcut = "execution(* com.wipro.venu.service.impl.*.*(..))", returning = "result")
    public void logAfterReturning(JoinPoint joinPoint, Object result) {
        log.info("Method executed: {} | Return value: {}",
                joinPoint.getSignature().toShortString(),
                result);
    }

    // After exception is thrown
    @AfterThrowing(pointcut = "execution(* com.wipro.venu.service.impl.*.*(..))", throwing = "ex")
    public void logAfterThrowing(JoinPoint joinPoint, Throwable ex) {
        log.error("Exception in method: {} | Message: {}",
                joinPoint.getSignature().toShortString(),
                ex.getMessage(), ex);
    }

    // Around advice to measure execution time
    @Around("execution(* com.wipro.venu.service.impl.*.*(..))")
    public Object logExecutionTime(ProceedingJoinPoint joinPoint) throws Throwable {
        long start = System.currentTimeMillis();
        try {
            Object result = joinPoint.proceed();
            long elapsedTime = System.currentTimeMillis() - start;
            log.info("Execution time of {} :: {} ms",
                    joinPoint.getSignature().toShortString(),
                    elapsedTime);
            return result;
        } catch (Throwable ex) {
            long elapsedTime = System.currentTimeMillis() - start;
            log.error("Exception in {} after {} ms | Message: {}",
                    joinPoint.getSignature().toShortString(),
                    elapsedTime,
                    ex.getMessage(), ex);
            throw ex;
        }
    }
}
