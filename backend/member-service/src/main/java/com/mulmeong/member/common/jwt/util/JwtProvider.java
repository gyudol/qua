package com.mulmeong.member.common.jwt.util;

import com.mulmeong.member.auth.domain.Member;
import com.mulmeong.member.auth.security.MemberDetails;
import com.mulmeong.member.common.exception.BaseException;
import com.mulmeong.member.common.jwt.properties.JwtProperties;
import com.mulmeong.member.common.response.BaseResponseStatus;
import io.jsonwebtoken.*;
import io.jsonwebtoken.security.Keys;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Service;

import javax.crypto.SecretKey;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

/**
 * JWT 토큰 생성 / 검증을 위한 Provider 클래스.
 *
 * @author Seong Gwang Ju.
 */
@RequiredArgsConstructor
@Slf4j
@Service
public class JwtProvider {

    private final JwtProperties jwtProperties;
    private SecretKey secretKey;

    //토큰에서 Authentication 객체를 생성하는 메서드
    public Authentication createAuthentication(String token) {

        Claims claims = parseClaims(token);
        Set<SimpleGrantedAuthority> authorities = new HashSet<>();

        MemberDetails memberDetails = new MemberDetails(
                Member.builder()
                        .memberUuid(claims.getSubject())
                        .build());

        log.info("claims : {}", claims);

        return new UsernamePasswordAuthenticationToken(
                memberDetails,
                token,
                authorities);
    }

    /**
     * 토큰 발급 메서드.
     *
     * @param memberUuid 회원의 uuid
     * @param expiredAt  토큰 유효시간(밀리초, 설정 시간 이후 만료되도록 설정)
     * @return JWT
     */
    public String generateToken(String memberUuid, long expiredAt) {
        Date now = new Date();
        Date expiry = new Date(now.getTime() + expiredAt);
        Claims claims = Jwts.claims().subject(memberUuid).build();

        return Jwts.builder()
                .header().add("typ", "JWT").and()
                .issuer(jwtProperties.getIssuer()) //토큰 발급자
                .issuedAt(now) //토큰 발급 시간
                .expiration(expiry) //토큰 만료 시간
                .subject(claims.getSubject()) //토큰 제목(memberUuid)
                .signWith(getSecretKey()) //토큰 서명
                .compact();
    }

    /**
     * 토큰 검증 : 파싱시 예외가 발생하면 false 반환.
     *
     * @param token JWT
     * @return 유효하면 true, 문제가 있으면 false
     */
    public boolean isValidToken(String token) {
        try {
            parseClaims(token.trim());
            return true;
        } catch (Exception e) {
            logAndThrow("토큰이 유효하지 않습니다", e);
            return false;
        }
    }

    /**
     * 토큰 파싱 : 예외 발생시 로그 + 예외 처리.
     *
     * @param token JWT
     */
    private Claims parseClaims(String token) {
        try {
            if (token == null) {
                throw new BaseException(BaseResponseStatus.NO_JWT_TOKEN);
            }
            return Jwts.parser()
                    .verifyWith(getSecretKey())
                    .build()
                    .parseSignedClaims(token)
                    .getPayload();
        } catch (ExpiredJwtException e) {
            logAndThrow("만료된 토큰입니다", e);
        } catch (UnsupportedJwtException e) {
            logAndThrow("지원되지 않는 유형의 토큰입니다", e);
        } catch (MalformedJwtException | IllegalArgumentException e) {
            logAndThrow("잘못된 토큰입니다", e);
        } catch (io.jsonwebtoken.security.SignatureException e) {
            logAndThrow("SecretKey가 일치하지 않습니다", e);
        } catch (Exception e) {
            logAndThrow("토큰이 유효하지 않습니다", e);
        }
        return null;
    }

    // 로그 + 예외
    private void logAndThrow(String message, Exception e) {
        log.error(message, e);
        throw new BaseException(BaseResponseStatus.WRONG_JWT_TOKEN);
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