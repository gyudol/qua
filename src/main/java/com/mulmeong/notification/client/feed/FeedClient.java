package com.mulmeong.notification.client.feed;

import com.mulmeong.notification.common.response.BaseResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.Optional;

@FeignClient(name = "feed-read-service", url = "${api.feed-read-service.url}")
public interface FeedClient {
    @GetMapping("/feeds/{feedUuid}")
    BaseResponse<FeedDto> getSingleFeed(@PathVariable String feedUuid);
}
