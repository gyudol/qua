package com.mulmeong.member.common.jwt.util;

import com.mulmeong.member.common.exception.BaseException;
import com.mulmeong.member.common.jwt.properties.JwtProperties;
import com.mulmeong.member.common.response.BaseResponseStatus;
import io.jsonwebtoken.*;
import io.jsonwebtoken.security.Keys;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.crypto.SecretKey;
import java.util.Date;

@RequiredArgsConstructor
@Slf4j
@Service
public class JwtProvider {

    private final JwtProperties jwtProperties;
    private SecretKey secretKey;

    //토큰 생성 메서드
    public String generateToken(String memberUuid, long expiredAt) {
        Date now = new Date();
        Date expiry = new Date(now.getTime() + expiredAt);
        Claims claims = Jwts.claims().subject(memberUuid).build();

        return Jwts.builder()
                .header().add("typ", "JWT").and()
                .issuer(jwtProperties.getIssuer()) //토큰 발급자
                .issuedAt(now) //토큰 발급 시간
                .expiration(expiry) //토큰 만료 시간
                .subject(claims.getSubject()) //토큰 제목(nickname)
                .signWith(getSecretKey()) //토큰 서명
                .compact();
    }

    // SecretKey 생성
    public SecretKey getSecretKey() {
        if (secretKey == null) {
            String secret = jwtProperties.getSecretKey();
            if (secret == null) {
                throw new RuntimeException("SecretKey 환경변수가 설정되지 않았습니다.");
            }
            secretKey = Keys.hmacShaKeyFor(secret.getBytes());
        }
        return secretKey;
    }
}