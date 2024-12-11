package com.mulmeong.notification.client.common;

import com.mulmeong.notification.common.exception.BaseException;
import com.mulmeong.notification.common.response.BaseResponseStatus;
import feign.Response;
import feign.codec.ErrorDecoder;

public class FeignErrorDecoder implements ErrorDecoder {
    @Override
    public Exception decode(String methodKey, Response response) {
        return new BaseException(BaseResponseStatus.ILLEGAL_ARGUMENT);
    }
}
