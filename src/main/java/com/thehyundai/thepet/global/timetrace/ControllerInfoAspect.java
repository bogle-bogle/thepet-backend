package com.thehyundai.thepet.global.timetrace;

import lombok.Getter;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.core.annotation.AnnotationUtils;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Getter
@Aspect
@Component
public class ControllerInfoAspect {

    private String baseUrl;

    @Before("@within(org.springframework.web.bind.annotation.RestController)")
    public void beforeController(JoinPoint joinPoint) {
        Class<?> controllerClass = joinPoint.getTarget().getClass();
        RestController restControllerAnnotation = AnnotationUtils.findAnnotation(controllerClass, RestController.class);
        if (restControllerAnnotation != null) {
            RequestMapping requestMapping = AnnotationUtils.findAnnotation(controllerClass, RequestMapping.class);
            if (requestMapping != null && requestMapping.value().length > 0) {
                baseUrl = requestMapping.value()[0];
            }
        }
    }

}
