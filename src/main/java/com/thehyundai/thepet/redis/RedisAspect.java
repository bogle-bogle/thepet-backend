package com.thehyundai.thepet.redis;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class RedisAspect {

    private final RedisService redisService;

    @Autowired
    public RedisAspect(RedisService cacheService) {
        this.redisService = cacheService;
    }

    @Pointcut("execution(public * com..controller.*.*(..))")
    public void cacheableMethods() {
    }

    @AfterReturning(pointcut = "cacheableMethods()", returning = "result")
    public void cacheData(JoinPoint joinPoint, Object result) {
        // API 엔드포인트에서 반환된 데이터를 캐시
        String key = generateCacheKey(joinPoint);
        String data = serializeData(result);
        redisService.cacheData(key, data, 3600); // 예: 1시간 동안 캐시
    }

    private String generateCacheKey(JoinPoint joinPoint) {
        // API 요청에 따라 고유한 캐시 키 생성 로직을 구현
        // 여기서는 간단하게 클래스명과 메서드명을 조합하여 사용
        String className = joinPoint.getTarget().getClass().getName();
        String methodName = joinPoint.getSignature().getName();
        return className + ":" + methodName;
    }

    private String serializeData(Object data) {
        // 데이터를 직렬화하여 캐시에 저장할 형식으로 변환하는 로직을 구현
        // 여기서는 가정으로 toString() 메서드를 호출한 결과를 반환
        return data.toString();
    }
}
