package com.mulmeong.comment.common.config;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.models.Components;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.security.SecurityRequirement;
import io.swagger.v3.oas.models.security.SecurityScheme;
import io.swagger.v3.oas.models.servers.Server;
import lombok.RequiredArgsConstructor;
import org.springdoc.core.models.GroupedOpenApi;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@OpenAPIDefinition(
        info = @Info(title = "댓글 도메인 API", version = "v1",
                description = "피드, 쇼츠 댓글 서비스",
                termsOfService = "http://swagger.io/terms/")

)
@Configuration
@RequiredArgsConstructor
public class SwaggerConfig {

// 상단 우측, 그룹화 + 넣고싶은 API 경로만 지정
//    @Bean GroupedOpenApi customOpenApi() {
//        String[] paths = {"/api/v1/member/**", "api/v1/auth/**", "/api/v1/health-check/**"};
//
//        return GroupedOpenApi.builder()
//                .group("멤버 도메인에 대한 API")
//                .pathsToMatch(paths)
//                .build();
//    }

    private static final String BEARER_TOKEN_PREFIX = "Bearer";

    //Authorize 버튼
    @Bean
    public OpenAPI openAPI() {

        String securityJwtName = "JWT";
        SecurityRequirement securityRequirement = new SecurityRequirement().addList(securityJwtName);
        Components components = new Components()
                .addSecuritySchemes(securityJwtName, new SecurityScheme()
                        .name(securityJwtName)
                        .type(SecurityScheme.Type.HTTP)
                        .scheme(BEARER_TOKEN_PREFIX)
                        .bearerFormat(securityJwtName));

        return new OpenAPI()
                .addSecurityItem(securityRequirement)
                .components(components)
                // Swagger에서 요청보낼때 API에 추가되는 문자열
                .addServersItem(new Server().url("/comment-service"));
    }
}