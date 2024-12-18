package com.mulmeong.feed.read.common.client;

import com.mulmeong.feed.read.common.response.BaseResponse;
import java.util.List;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "utility-service", url = "${api.utility-service.url}")
public interface UtilityClient {

    @GetMapping("/members/{memberUuid}/all/followings")
    BaseResponse<List<String>> getAllFollowings(@PathVariable String memberUuid);

}
