package com.qycloud.oatos.license.monitor;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.stereotype.Component;

/**
 * Created by jiuyuehe on 2015/1/23.
 */

@Aspect
@Component
public class AopMonitor {

    @Before("execution(* com.qycloud.oatos.license.web.*Controller.*(..))")
    public void forbidAccess(JoinPoint joinPoint){

        System.out.println("--aspect----before---");

        System.out.println("Completed: " + joinPoint.getSignature());
        System.out.println("Completed: " + joinPoint.getTarget());
        System.out.println("Completed: " + joinPoint.getArgs());
        System.out.println("Completed: " + joinPoint.getSourceLocation());

    }

    @AfterReturning("execution(* com.qycloud.oatos.license.web.*Controller.*(..))")
    public void logAccess(JoinPoint joinPoint){
        System.out.println("---aspect---after---");
        System.out.println("Completed: " + joinPoint);
    }

}
