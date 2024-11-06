package com.mulmeong.member.common.response;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;

@Getter
@AllArgsConstructor
public enum BaseResponseStatus {
    /*
     * 응답 코드와 메시지 표준화하는 ENUM.
     * Http 상태코드, 성공 여부, 응답 메시지, 커스텀 응답 코드, 데이터를 반환.
     */

    // 200: 요청 성공.
    SUCCESS(HttpStatus.OK, true, 200, "요청에 성공하였습니다."),

    // 400 : 사용자 요청 오류
    ILLEGAL_ARGUMENT(HttpStatus.BAD_REQUEST, false, 400, "잘못된 요청입니다."),
    INVALID_INPUT_VALUE(HttpStatus.BAD_REQUEST, false, 401, "적절하지 않은 요청값입니다."),
    NO_SIGN_IN(HttpStatus.UNAUTHORIZED, false, 402, "로그인을 먼저 진행해주세요"),

    // 600 : Auth
    WRONG_JWT_TOKEN(HttpStatus.UNAUTHORIZED, false, 600, "다시 로그인 해주세요"),
    NO_JWT_TOKEN(HttpStatus.UNAUTHORIZED, false, 601, "토큰이 없습니다."),
    TOKEN_NOT_VALID(HttpStatus.UNAUTHORIZED, false, 602, "토큰이 유효하지 않습니다."),
    NO_SUPPORTED_PROVIDER(HttpStatus.BAD_REQUEST, false, 603, "지원하지 않는 플랫폼입니다."),
    INVALID_OAUTH(HttpStatus.BAD_REQUEST, false, 604, "올바르지 않은 소셜 계정입니다."),
    NO_EXIST_USER(HttpStatus.NOT_FOUND, false, 605, "존재하지 않는 회원 정보입니다."),
    FAILED_TO_LOGIN(HttpStatus.UNAUTHORIZED, false, 606, "로그인에 실패하였습니다."),
    DISABLED_USER(HttpStatus.UNAUTHORIZED, false, 607, "비활성화된 계정입니다."),
    NO_ACCESS_AUTHORITY(HttpStatus.FORBIDDEN, false, 608, "접근 권한이 없습니다."),

    // 900 : 기타 에러
    INTERNAL_SERVER_ERROR(HttpStatus.INTERNAL_SERVER_ERROR, false, 900, "요청 처리 중 에러가 발생하였습니다."),


    // 1100 : Member 관련
    NOT_FOUND_MEMBER(HttpStatus.NOT_FOUND, false, 1100, "존재하지 않는 회원입니다."),
    EXISTS_NICKNAME(HttpStatus.CONFLICT, false, 1101, "이미 존재하는 닉네임입니다.");

    private final HttpStatusCode httpStatusCode;
    private final boolean isSuccess;
    private final int code;
    private final String message;
}
