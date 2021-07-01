package com.example.securitydemo.handlers.aspect;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;

import java.time.Instant;

/**
 * @author jia
 */
//@Aspect
@Component
@Slf4j
public class TimeAspect {

    @Around("execution(* com.example.securitydemo.controller.DemoController.*(..))")
    public Object handleControllerMethod(ProceedingJoinPoint proceedingJoinPoint) throws Throwable {
        long l = Instant.now().toEpochMilli();
        Object proceed = proceedingJoinPoint.proceed();
        System.out.println(proceed);
        for (Object arg : proceedingJoinPoint.getArgs()) {
            System.out.println(arg);
        }
        log.info("耗时: {} ms", Instant.now().toEpochMilli() - l);
        return proceed;
    }


}
