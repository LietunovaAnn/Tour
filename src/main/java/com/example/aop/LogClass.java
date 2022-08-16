package com.example.aop;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.stereotype.Component;

@Component
@Aspect
public class LogClass {
    @Before("execution(* com.example.*.*.*())")
    public void logMethod(JoinPoint joinPoint) {
        System.out.println("xxxxxx");
        System.out.println("Details: " + joinPoint.getSignature().getName());
        System.out.println("xxxxxx");
        //   log.info("LOG.INFO FROM LOG ASPECT Method " + joinPoint.getSignature().getName() + " started");
    }
}
