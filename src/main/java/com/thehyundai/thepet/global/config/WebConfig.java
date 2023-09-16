package com.thehyundai.thepet.global.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebConfig implements WebMvcConfigurer {

    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/**")
                .allowedOrigins("http://heendy-front.s3-website.ap-northeast-2.amazonaws.com", "http://localhost:3000", "http://localhost:8000", "https://thepet.thehyundai.site")
                .allowedMethods("GET", "POST", "PUT", "DELETE");
    }
}