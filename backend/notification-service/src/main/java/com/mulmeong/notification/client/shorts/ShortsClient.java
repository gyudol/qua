package com.mulmeong.notification.client.shorts;

import com.mulmeong.notification.common.response.BaseResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "shorts-read-service", url = "${api.shorts-read-service.url}")
public interface ShortsClient {
    @GetMapping("/shorts/{shortsUuid}")
    BaseResponse<ShortsDto> getSingleShorts(@PathVariable String shortsUuid);

}
