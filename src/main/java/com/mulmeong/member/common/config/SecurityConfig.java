package com.mulmeong.member.common.config;

import com.mulmeong.member.common.exception.CustomAuthenticationEntryPoint;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;

@RequiredArgsConstructor
@Configuration
@EnableWebSecurity
public class SecurityConfig {

    private final AuthenticationProvider daoAuthenticationProvider;

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                .csrf(AbstractHttpConfigurer::disable) // CSRF 설정 비활성화
                .formLogin(AbstractHttpConfigurer::disable) // Form 로그인 설정 비활성화
                .httpBasic(AbstractHttpConfigurer::disable) // HTTP Basic 설정 비활성화
                .logout(AbstractHttpConfigurer::disable) // 로그아웃 설정 비활성화
                .authorizeHttpRequests(
                        authorizeRequests -> authorizeRequests
                                //.requestMatchers(인증이 필요한 API)
                                //.authenticated()
                                .anyRequest()
                                .permitAll()
                )

                .sessionManagement(
                        sessionManagement -> sessionManagement
                                .sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                )

                .exceptionHandling(exceptionHandling -> exceptionHandling.authenticationEntryPoint(
                        new CustomAuthenticationEntryPoint()))

                .authenticationProvider(daoAuthenticationProvider);
        return http.build();
    }

}
