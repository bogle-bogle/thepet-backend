package com.thehyundai.thepet.global.timetrace;

import lombok.extern.log4j.Log4j2;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.StopWatch;

import java.lang.reflect.Method;

@Log4j2
@Component
@Aspect
public class TimeTraceAspect {
    @Autowired
    private AopMapper aopMapper;

    @Pointcut("@annotation(com.thehyundai.thepet.global.timetrace.TimeTraceService)")
    private void timeTraceServicePointcut() {
    }

    @Pointcut("@annotation(com.thehyundai.thepet.global.timetrace.TimeTraceController)")
    private void timeTraceControllerPointcut() {
    }

    @Around("timeTraceServicePointcut() && @annotation(timeTrace)")
    public Object traceTime(ProceedingJoinPoint joinPoint, TimeTraceService timeTrace) throws Throwable {
        StopWatch stopWatch = new StopWatch();

        try {
            stopWatch.start();
            return joinPoint.proceed(); // 실제 타겟 호출
        } finally {
            stopWatch.stop();
            double executionTimeMillis = stopWatch.getTotalTimeMillis();
            String executionTimeFormatted = String.format("%.2f", executionTimeMillis);
            String methodName = getMethodName(joinPoint);
            String requestName =getClassName(joinPoint);

            AopServiceVO timeTraceServiceInfo = new AopServiceVO();
            timeTraceServiceInfo.setMethodName(methodName);
            timeTraceServiceInfo.setExecutionTime(executionTimeFormatted);
            timeTraceServiceInfo.setRequestName(requestName);

            aopMapper.saveAOPServiceTable(timeTraceServiceInfo);
            // timeTraceInfo 객체를 원하는 대로 로깅하거나 데이터베이스에 저장
            log.info("URL: {}, Method: {}, Execution Time: {}ms", requestName, methodName, executionTimeFormatted);
        }
    }

    @Around("timeTraceControllerPointcut() && @annotation(timeTrace)")
    public Object traceTime(ProceedingJoinPoint joinPoint, TimeTraceController timeTrace) throws Throwable {
        StopWatch stopWatch = new StopWatch();

        try {
            stopWatch.start();
            return joinPoint.proceed(); // 실제 타겟 호출
        } finally {
            stopWatch.stop();
            double executionTimeMillis = stopWatch.getTotalTimeMillis();
            String executionTimeFormatted = String.format("%.2f", executionTimeMillis);
            String methodName = getMethodName(joinPoint);
            String requestName =getClassName(joinPoint);
            String parameterName = getParameter(joinPoint);

            AopControllerVO timeTraceControllerInfo = new AopControllerVO();
            timeTraceControllerInfo.setParameterName(parameterName);
            timeTraceControllerInfo.setMethodName(methodName);
            timeTraceControllerInfo.setExecutionTime(executionTimeFormatted);

            aopMapper.saveAOPControllerTable(timeTraceControllerInfo);
            // timeTraceInfo 객체를 원하는 대로 로깅하거나 데이터베이스에 저장
            log.info("URL: {}, Method: {}, Execution Time: {}ms", requestName, methodName, executionTimeFormatted);
        }
    }

    // 메소드명 가져오기
    public String getMethodName(JoinPoint joinPoint) {
        MethodSignature signature = (MethodSignature) joinPoint.getSignature();
        Method method = signature.getMethod();
        return method.getName();
    }

    // 클래스명 가져오기
    public String getClassName(JoinPoint joinPoint) {
        Class<?> activeClass = joinPoint.getTarget().getClass();
        String fullClassName = activeClass.getName();

        // 클래스 이름을 '.'으로 분리하고 마지막 요소 가져오기
        String[] classNameParts = fullClassName.split("\\.");
        return classNameParts[classNameParts.length - 1];
    }

    // 매개변수 가져오기
    public String getParameter(JoinPoint joinPoint) {

        StringBuilder parameters = new StringBuilder();

        for (Object it : joinPoint.getArgs()) {
            parameters.append(" ");
            parameters.append(it.toString());
        }

        return parameters.toString();
    }

}
