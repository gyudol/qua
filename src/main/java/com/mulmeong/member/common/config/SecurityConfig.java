package com.mulmeong.member.common.config;

import com.mulmeong.member.common.exception.CustomAuthenticationEntryPoint;
import com.mulmeong.member.common.jwt.filter.JwtAuthFilter;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@RequiredArgsConstructor
@Configuration
@EnableWebSecurity
public class SecurityConfig {

    private final AuthenticationProvider daoAuthenticationProvider;
    private final JwtAuthFilter jwtAuthFilter;

    /**
     * Spring Security filter 설정.
     * todo: Gateway의 GlobalFilter가 명확해질 때 동일하게 적용
     *
     * @param http http 요청에 대한 보안 설정
     * @return SecurityFilterChain
     * @throws Exception 예외 처리
     */
    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                .csrf(AbstractHttpConfigurer::disable) // CSRF 설정 비활성화
                .formLogin(AbstractHttpConfigurer::disable) // Form 로그인 설정 비활성화
                .httpBasic(AbstractHttpConfigurer::disable) // HTTP Basic 설정 비활성화
                .logout(AbstractHttpConfigurer::disable) // 로그아웃 설정 비활성화
                .authorizeHttpRequests(
                        authorizeRequests -> authorizeRequests
                                //인증이 필요없는 URL 정의
                                .requestMatchers(HttpMethod.GET,
                                        "/v1/members/{memberUuid}/nickname", // 닉네임 조회
                                        "/v1/members/{memberUuid}/interests/**" // 관심 카테고리,해시태그 조회
                                ).permitAll()
                                .requestMatchers(
                                        "/actuator/**", // actuator
                                        "/v1/auth/**", // 회원가입/로그인, 토큰 재발급
                                        "/v1/health-check/**", //헬스 체크
                                        "/v3/api-docs/**", "/swagger-ui/**", "/swagger-ui.html" // Swagger
                                ).permitAll()
                                // 나머지 API는 인증이 필요
                                .anyRequest()
                                .authenticated()
                )

                .sessionManagement(
                        sessionManagement -> sessionManagement
                                .sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                )

                .exceptionHandling(exceptionHandling -> exceptionHandling.authenticationEntryPoint(
                        new CustomAuthenticationEntryPoint()))

                .addFilterBefore(jwtAuthFilter, UsernamePasswordAuthenticationFilter.class)

                // CORS필터의 경우 Gateway의 GlobalFilter가 명확해질 때 동일하게 적용
                .authenticationProvider(daoAuthenticationProvider);
        return http.build();
    }
}
