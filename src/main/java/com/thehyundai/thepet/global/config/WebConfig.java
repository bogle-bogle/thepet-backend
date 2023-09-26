package com.thehyundai.thepet.global.config;

import com.thehyundai.thepet.global.jwt.AuthTokensGenerator;
import com.thehyundai.thepet.global.jwt.AuthorizationInterceptor;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebConfig implements WebMvcConfigurer {
    private final AuthTokensGenerator authTokensGenerator;

    public WebConfig(AuthTokensGenerator authTokensGenerator) {
        this.authTokensGenerator = authTokensGenerator;
    }

    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/**")
                .allowedOrigins("http://heendy-front.s3-website.ap-northeast-2.amazonaws.com",
                                "http://localhost:3000",
                                "http://localhost:8000",
                                "https://thepet.thehyundai.site")
                .allowedMethods("GET", "POST", "PUT", "DELETE", "PATCH");
    }

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(new AuthorizationInterceptor(authTokensGenerator))
                .addPathPatterns("/**")
                .excludePathPatterns("/api/shop",
                                     "/api/thepetbox",
                                     "/api/jwt-error/**",
                                     "/api/product/**",
                                     "/api/member/login",
                                     "/api/curation/**",
                                     "/api/backoffice/**",
                                     "/api/review/**",
                                     "/api/hc/branch",
                                     "/api/event",
                                     "/api/pet",
                                     "/api/hc/branch/{branchCode}/reservation",
                                     "/api/hc/updateStatus/{productId}/{type}/{newValue}",
                                     "/docs", "/swagger-ui/**", "/v3/api-docs/**", "/swagger-resources/**", "/webjars/**");
    }
}