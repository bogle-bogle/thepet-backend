package com.thehyundai.thepet.global.timetrace;

import com.thehyundai.thepet.global.kafka.ControllerLogProducer;
import com.thehyundai.thepet.global.kafka.ServiceLogProducer;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.stereotype.Component;
import org.springframework.util.StopWatch;
import org.springframework.web.bind.annotation.*;

import java.lang.reflect.Method;

import static com.thehyundai.thepet.global.util.Constant.JWT_PREFIX;

@Log4j2
@Component
@Aspect
@RequiredArgsConstructor
public class TimeTraceAspect {
    private final ControllerInfoAspect controllerInfoAspect;
    private final ServiceLogProducer serviceLogProducer;
    private final ControllerLogProducer controllerLogProducer;

    @Pointcut("@within(com.thehyundai.thepet.global.timetrace.ServiceTimeTrace)")
    private void timeTraceServicePointcut() {
    }

    @Pointcut("@within(com.thehyundai.thepet.global.timetrace.ControllerTimeTrace)")
    private void timeTraceControllerPointcut(){
    }

    @Around("timeTraceServicePointcut() && @within(timeTrace)")
    public Object traceTime(ProceedingJoinPoint joinPoint, ServiceTimeTrace timeTrace) throws Throwable {
        StopWatch stopWatch = new StopWatch();

        try {
            stopWatch.start();
            return joinPoint.proceed(); // 실제 타겟 호출
        } finally {
            stopWatch.stop();
            double executionTimeMillis = stopWatch.getTotalTimeMillis();
            double executionTimeSeconds = executionTimeMillis / 1000.0; // 밀리초를 초로 변환
            String executionTimeFormatted = String.format("%.2f", executionTimeSeconds); // 소수점 두 자리까지 표시
            String methodName = getMethodName(joinPoint);
            String requestName =getClassName(joinPoint);

            AopServiceVO timeTraceServiceInfo = new AopServiceVO();
            timeTraceServiceInfo.setMethodName(methodName);
            timeTraceServiceInfo.setExecutionTime(executionTimeFormatted);
            timeTraceServiceInfo.setRequestName(requestName);

            serviceLogProducer.sendMessage(timeTraceServiceInfo);

            log.info("URL: {}, Method: {}, Execution Time: {}ms", requestName, methodName, executionTimeFormatted);
        }
    }

    @Around("timeTraceControllerPointcut() && @within(timeTrace)")
    public Object traceTime(ProceedingJoinPoint joinPoint, ControllerTimeTrace timeTrace) throws Throwable {
        StopWatch stopWatch = new StopWatch();

        try {
            stopWatch.start();
            return joinPoint.proceed(); // 실제 타겟 호출
        } finally {
            stopWatch.stop();
            double executionTimeMillis = stopWatch.getTotalTimeMillis();
            double executionTimeSeconds = executionTimeMillis / 1000.0; // 밀리초를 초로 변환
            String executionTimeFormatted = String.format("%.2f", executionTimeSeconds); // 소수점 두 자리까지 표시
            String methodName = getMethodName(joinPoint);


            String requestMapping =getRequestMapping(joinPoint);
            String parameterName = getParameter(joinPoint);

            AopControllerVO timeTraceControllerInfo = new AopControllerVO();
            timeTraceControllerInfo.setParameterName(parameterName);
            timeTraceControllerInfo.setMethodName(methodName);
            timeTraceControllerInfo.setRequestMapping(requestMapping);
            timeTraceControllerInfo.setExecutionTime(executionTimeFormatted);

            controllerLogProducer.sendMessage(timeTraceControllerInfo);
            log.info("URL: {}, Method: {}, Execution Time: {}ms", requestMapping, methodName, executionTimeFormatted);
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
            if (it instanceof String && ((String) it).startsWith(JWT_PREFIX)) {
                // 'Bearer'로 시작하는 문자열이면 null 반환
                return null;
            }
            parameters.append(" ");
            parameters.append(it.toString());
        }

        return parameters.toString();
    }


    // 매핑된 URL 가져오기
    public String getRequestMapping(JoinPoint joinPoint) {
        MethodSignature signature = (MethodSignature) joinPoint.getSignature();
        Method method = signature.getMethod();


        String baseUrl = controllerInfoAspect.getBaseUrl();
        GetMapping getMappingAnnotation = method.getAnnotation(GetMapping.class);
        if (getMappingAnnotation != null && getMappingAnnotation.value().length > 0) {
            return baseUrl + getMappingAnnotation.value()[0];
        }

        // PostMapping 처리
        PostMapping postMappingAnnotation = method.getAnnotation(PostMapping.class);
        if (postMappingAnnotation != null && postMappingAnnotation.value().length > 0) {
            return baseUrl + postMappingAnnotation.value()[0];
        }

        // DeleteMapping 처리
        DeleteMapping deleteMappingAnnotation = method.getAnnotation(DeleteMapping.class);
        if (deleteMappingAnnotation != null && deleteMappingAnnotation.value().length > 0) {
            return baseUrl + deleteMappingAnnotation.value()[0];
        }

        // PatchMapping 처리
        PatchMapping patchMappingAnnotation = method.getAnnotation(PatchMapping.class);
        if (patchMappingAnnotation != null && patchMappingAnnotation.value().length > 0) {
            return baseUrl + patchMappingAnnotation.value()[0];
        }

        // PutMapping 처리
        PutMapping putMappingAnnotation = method.getAnnotation(PutMapping.class);
        if (putMappingAnnotation != null && putMappingAnnotation.value().length > 0) {
            return baseUrl + putMappingAnnotation.value()[0];
        }

        // 기본 URL 반환
        return baseUrl;
    }
}
