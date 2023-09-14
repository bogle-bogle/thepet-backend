package com.thehyundai.thepet.global.timetrace;

import com.thehyundai.thepet.domain.mypet.pet.PetServiceImpl;
import lombok.extern.log4j.Log4j2;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.StopWatch;

@Log4j2
@Component
@Aspect
public class TimeTraceAspect {
    @Autowired
    private AopMapper aopMapper;

    @Pointcut("@annotation(com.thehyundai.thepet.global.timetrace.TimeTrace)")
    private void timeTracePointcut() {
    }

    @Around("timeTracePointcut() && @annotation(timeTrace)")
    public Object traceTime(ProceedingJoinPoint joinPoint, TimeTrace timeTrace) throws Throwable {
        StopWatch stopWatch = new StopWatch();

        try {
            stopWatch.start();
            return joinPoint.proceed(); // 실제 타겟 호출
        } finally {
            stopWatch.stop();
            String executionTime = String.valueOf(stopWatch.getTotalTimeMillis());
            String methodName = timeTrace.methodName();
            String requestName = timeTrace.requestName();

            AopVO timeTraceInfo = new AopVO();
            timeTraceInfo.setMethodName(methodName);
            timeTraceInfo.setExecutionTime(executionTime);
            timeTraceInfo.setRequestName(requestName);

            aopMapper.saveAOPTable(timeTraceInfo);
            // timeTraceInfo 객체를 원하는 대로 로깅하거나 데이터베이스에 저장
            log.info("URL: {}, Method: {}, Execution Time: {}ms", requestName, methodName, executionTime);
        }
    }

    @Around("@annotation(timeTrace)") // @TimeTrace 어노테이션이 있는 메서드 주변에 실행
    public Object aroundTimeTrace(ProceedingJoinPoint joinPoint, TimeTrace timeTrace) throws Throwable {
        String requestName = timeTrace.requestName();
        String methodName = timeTrace.methodName();

        // requestName과 methodName을 원하는 작업에 활용할 수 있음
        // 여기서는 메서드 파라미터로 전달
        ((AopVO) joinPoint.getTarget()).setRequestName(requestName);
        ((AopVO) joinPoint.getTarget()).setMethodName(methodName);

        try {
            // 원래 메서드 실행
            Object result = joinPoint.proceed();
            return result;
        } catch (Throwable throwable) {
            throw throwable;
        }
    }
}
