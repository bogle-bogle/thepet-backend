package com.thehyundai.thepet.global.timetrace;

import lombok.extern.log4j.Log4j2;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
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
}
