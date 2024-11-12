package com.mulmeong.utility.common.response;

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


    /**
     * 200: 요청 성공.
     **/
    SUCCESS(HttpStatus.OK, true, 200, "요청에 성공하였습니다."),

    DUPLICATE_FOLLOW(HttpStatus.BAD_REQUEST,false, 702, "이미 팔로우 중인 유저입니다."),
    NOT_EXIST(HttpStatus.BAD_REQUEST,false, 703, "존재하지 않는 데이터입니다."),

    /**
     * 900: 기타 에러.
     */
    INTERNAL_SERVER_ERROR(HttpStatus.INTERNAL_SERVER_ERROR, false, 900, "요청 처리 중 에러가 발생하였습니다.");

//  이 위에 본인이 사용할 커스텀 예외를 추가해주세요.

    private final HttpStatusCode httpStatusCode;
    private final boolean isSuccess;
    private final int code;
    private final String message;
    }