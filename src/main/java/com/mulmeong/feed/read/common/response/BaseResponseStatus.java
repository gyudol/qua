package com.mulmeong.feed.read.common.response;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
@AllArgsConstructor
public enum BaseResponseStatus {

    /**
     * 200: 요청 성공.
     **/
    SUCCESS(HttpStatus.OK, true, 200, "요청에 성공하였습니다."),

    /**
     * 400: 사용자 요청 에러.
     */
    ILLEGAL_ARGUMENT(HttpStatus.BAD_REQUEST, false, 400, "잘못된 요청입니다."),
    INVALID_INPUT_VALUE(HttpStatus.BAD_REQUEST, false, 401, "적절하지 않은 요청값입니다."),

    /**
     * 900: 기타 에러.
     */
    INTERNAL_SERVER_ERROR(HttpStatus.INTERNAL_SERVER_ERROR, false, 900, "서버에서 예기치 않은 오류가 발생했습니다."),

    /**
     * 1000: Feed Service 에러.
     */
    FEED_FORBIDDEN(HttpStatus.FORBIDDEN, false, 1003, "피드 접근 권한이 없습니다."),
    FEED_NOT_FOUND(HttpStatus.NOT_FOUND, false, 1004, "존재하지 않는 게시글 정보입니다."),
    HASHTAG_DUPLICATE_KEY(HttpStatus.CONFLICT, false, 1009, "해시태그가 이미 존재합니다.");

    private final HttpStatus httpStatusCode;
    private final boolean isSuccess;
    private final int code;
    private final String message;

}
