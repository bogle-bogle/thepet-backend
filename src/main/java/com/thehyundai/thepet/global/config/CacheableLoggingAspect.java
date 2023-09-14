package com.thehyundai.thepet.global.config;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class CacheableLoggingAspect {

    @Before("@annotation(org.springframework.cache.annotation.Cacheable)")
    public void beforeCacheableMethod(JoinPoint joinPoint) {
        System.out.println("Calling @Cacheable method: " + joinPoint.getSignature().toShortString());
    }

    @AfterReturning(pointcut = "@annotation(org.springframework.cache.annotation.Cacheable)", returning = "result")
    public void afterReturningCacheableMethod(JoinPoint joinPoint, Object result) {
        System.out.println("@Cacheable method returned: " + joinPoint.getSignature().toShortString());
    }
}
