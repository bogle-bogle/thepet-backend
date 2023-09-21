package com.thehyundai.thepet.global.config;

import io.swagger.v3.oas.models.Components;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.security.SecurityRequirement;
import io.swagger.v3.oas.models.security.SecurityScheme;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import static com.thehyundai.thepet.global.util.Constant.HEADER_TOKEN_PARAM;
import static com.thehyundai.thepet.global.util.Constant.JWT_PREFIX;

// 공식문서 : https://springdoc.org/#Introduction
// Swagger UI 페이지 : http://localhost:8080/swagger-ui/index.html
// OpenAPI 설명 페이지 (json 형식) : http://localhost:8080/v3/api-docs
@Configuration
public class SwaggerConfig {
    @Bean
    public OpenAPI openAPI() {
        SecurityRequirement securityRequirement = new SecurityRequirement().addList(HEADER_TOKEN_PARAM);

        Components components = new Components()
                                    .addSecuritySchemes(HEADER_TOKEN_PARAM, new SecurityScheme()
                                    .name(HEADER_TOKEN_PARAM)
                                    .type(SecurityScheme.Type.HTTP)
                                    .in(SecurityScheme.In.HEADER)
                                    .scheme(JWT_PREFIX));

        return new OpenAPI()
                    .components(components)
                    .addSecurityItem(securityRequirement)
                    .info(apiInfo());
    }
    private Info apiInfo() {
        return new Info()
                .title("THE PET - API Documentation")
                .description("개인 맞춤형 반려동물 쇼핑몰, THE PET")
                .version("1.0.0");
    }
}