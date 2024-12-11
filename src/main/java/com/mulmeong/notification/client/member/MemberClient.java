package com.mulmeong.notification.client.member;

import com.mulmeong.notification.common.response.BaseResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "member-service", url = "${api.member-read-service.url}")
public interface MemberClient {
    @GetMapping("/members/{memberUuid}/compact-profile")
    BaseResponse<MemberDto> getCompactProfileByUuid(@PathVariable String memberUuid);
}
