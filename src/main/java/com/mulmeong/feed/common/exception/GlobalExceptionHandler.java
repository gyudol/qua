package com.mulmeong.feed.common.exception;

import static com.mulmeong.feed.common.response.BaseResponseStatus.INTERNAL_SERVER_ERROR;

import com.mulmeong.feed.common.response.BaseResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@Slf4j
@RestControllerAdvice
public class GlobalExceptionHandler {

    // 전역 예외 처리 및 JSON 형식의 일관된 에러 응답 제공

    /**
     * BaseException 예외 처리
     */
    @ExceptionHandler(BaseException.class)
    protected BaseResponse<Void> BaseError(BaseException e) {
        log.error("BaseException -> {} ({})", e.getStatus(), e.getStatus().getMessage(), e);

        return new BaseResponse<>(e.getStatus());
    }

    /**
     * 예기치 않은 에러
     * RuntimeException 예외 처리
     */
    @ExceptionHandler(RuntimeException.class)
    protected BaseResponse<Void> RuntimeError(RuntimeException e) {
        log.error("RuntimeException -> {}", e.getMessage(), e);

        return new BaseResponse<>(INTERNAL_SERVER_ERROR, e);
    }

}
