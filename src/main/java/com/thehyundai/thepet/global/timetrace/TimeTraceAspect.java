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

import java.lang.annotation.Annotation;
import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.List;

import static com.thehyundai.thepet.global.util.Constant.JWT_PREFIX;

@Log4j2
@Component
@Aspect
@RequiredArgsConstructor
public class TimeTraceAspect {
    private final ControllerInfoAspect controllerInfoAspect;
    private final ServiceLogProducer serviceLogProducer;
    private final ControllerLogProducer controllerLogProducer;
    private final List<Class<? extends Annotation>> mappingAnnotations = Arrays.asList(GetMapping.class, PostMapping.class, DeleteMapping.class, PutMapping.class);


    @Pointcut("@annotation(com.thehyundai.thepet.global.timetrace.ServiceTimeTrace)")
    private void timeTraceServicePointcut() {
    }

    @Pointcut("@annotation(com.thehyundai.thepet.global.timetrace.ControllerTimeTrace)")
    private void timeTraceControllerPointcut(){
    }

    @Around("timeTraceServicePointcut()")
    public Object serviceTraceTime(ProceedingJoinPoint joinPoint) throws Throwable {
        StopWatch stopWatch = new StopWatch();

        try {
            stopWatch.start();
            return joinPoint.proceed();
        } finally {
            stopWatch.stop();
            long executionTimeMillis = stopWatch.getTotalTimeMillis();
            AopServiceVO serviceLog = logServiceExecutionTime(joinPoint, executionTimeMillis);
            serviceLogProducer.sendMessage(serviceLog);
        }
    }

    @Around("timeTraceControllerPointcut()")
    public Object controllerTraceTime(ProceedingJoinPoint joinPoint) throws Throwable {
        StopWatch stopWatch = new StopWatch();
        log.info("adfasdfsdf");
        try {
            stopWatch.start();
            return joinPoint.proceed();
        } finally {
            stopWatch.stop();
            long executionTimeMillis = stopWatch.getTotalTimeMillis();
            AopControllerVO controllerLog = logControllerExecutionTime(joinPoint, executionTimeMillis);
            controllerLogProducer.sendMessage(controllerLog);
        }
    }

    private AopServiceVO logServiceExecutionTime(ProceedingJoinPoint joinPoint, long executionTimeMillis) {
        String methodName = getMethodName(joinPoint);
        String requestName = getClassName(joinPoint);

        return AopServiceVO.builder()
                .methodName(methodName)
                .executionTime(String.valueOf(executionTimeMillis))
                .requestName(requestName)
                .build();
    }

    private AopControllerVO logControllerExecutionTime(ProceedingJoinPoint joinPoint, long executionTimeMillis) {
        String methodName = getMethodName(joinPoint);
        String requestMapping = getRequestMapping(joinPoint);
        String parameterName = getParameter(joinPoint);

        return AopControllerVO.builder()
                .parameterName(parameterName)
                .methodName(methodName)
                .requestMapping(requestMapping)
                .executionTime(String.valueOf(executionTimeMillis))
                .build();
    }

    public String getMethodName(JoinPoint joinPoint) {    // 메소드명 가져오기
        MethodSignature signature = (MethodSignature) joinPoint.getSignature();
        Method method = signature.getMethod();
        return method.getName();
    }

    public String getClassName(JoinPoint joinPoint) {    // 클래스명 가져오기
        Class<?> activeClass = joinPoint.getTarget().getClass();
        String fullClassName = activeClass.getName();
        String[] classNameParts = fullClassName.split("\\.");        // 클래스 이름을 '.'으로 분리하고 마지막 요소 가져오기
        return classNameParts[classNameParts.length - 1];
    }

    public String getParameter(JoinPoint joinPoint) {    // 매개변수 가져오기

        StringBuilder parameters = new StringBuilder();

        for (Object it : joinPoint.getArgs()) {
            if (it instanceof String && ((String) it).startsWith(JWT_PREFIX)) {                // 'Bearer'로 시작하는 문자열이면 null 반환
                return null;
            }
            parameters.append(" ");
            parameters.append(it.toString());
        }

        return parameters.toString();
    }


    public String getRequestMapping(JoinPoint joinPoint) {      // 매핑된 URL 가져오기
        MethodSignature signature = (MethodSignature) joinPoint.getSignature();
        Method method = signature.getMethod();
        String baseUrl = controllerInfoAspect.getBaseUrl();

        for (Class<? extends Annotation> mappingAnnotation : mappingAnnotations) {
            Annotation annotation = method.getAnnotation(mappingAnnotation);
            if (annotation != null) {
                try {
                    String[] values = (String[]) annotation.getClass().getMethod("value").invoke(annotation);
                    if (values.length > 0) {
                        return baseUrl + values[0];
                    }
                } catch (Exception e) {
                }
            }
        }
        return baseUrl;
    }
}
