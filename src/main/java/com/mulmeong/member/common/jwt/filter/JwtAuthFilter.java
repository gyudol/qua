package com.mulmeong.member.common.jwt.filter;


import com.mulmeong.member.common.jwt.properties.JwtProperties;
import com.mulmeong.member.common.jwt.util.JwtProvider;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

@Slf4j
@RequiredArgsConstructor
@Component
public class JwtAuthFilter extends OncePerRequestFilter {

    private final JwtProperties jwtProperties;
    private final JwtProvider jwtProvider;

    @Override
    protected void doFilterInternal(
            HttpServletRequest request,
            HttpServletResponse response,
            FilterChain filterChain) throws ServletException, IOException {

        // 헤더에서 토큰을 가져옴("Bearer ey......")
        System.out.println(jwtProperties.getAccessTokenPrefix());
        String authHeader = request.getHeader(jwtProperties.getAccessTokenPrefix());


        // 헤더에 토큰이 없거나, 토큰 접두사가 "Bearer"가 아닌 경우 요청 중단
        if (authHeader == null || !authHeader.startsWith(jwtProperties.getTokenPrefix())) {
            log.error("헤더에 토큰이 없거나, 토큰 접두사가 이상합니다. 값: {}", authHeader);
            filterChain.doFilter(request, response);
            return;
        }

        String token = getAccessToken(authHeader); // "ey..."
        boolean validToken = jwtProvider.isValidToken(token);
        boolean isAuthenticated = SecurityContextHolder.getContext().getAuthentication() != null;
        log.info("토큰이 유효한지 : {}, 인증이 되어있는지 : {}", validToken, isAuthenticated);

        // 토큰이 유효하고 인증되어 있지 않다면, 토큰을 이용해 인증 객체 생성 => SecurityContext에 저장
        if (validToken && !isAuthenticated) {
            log.info("authentication 저장");
            Authentication authentication = jwtProvider.createAuthentication(token);
            SecurityContextHolder.getContext().setAuthentication(authentication);
        }
        filterChain.doFilter(request, response);
    }

    // 헤더에서 토큰을 가져오는 메서드
    private String getAccessToken(String requestHeader) {

        String prefix = jwtProperties.getTokenPrefix();

        if (requestHeader != null && requestHeader.startsWith(prefix)) {
            return requestHeader.substring(prefix.length()).trim(); //공백까지 제거
        }
        return null;
    }
}
