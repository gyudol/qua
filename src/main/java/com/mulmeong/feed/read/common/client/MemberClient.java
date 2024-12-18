package com.mulmeong.feed.read.common.client;

import com.mulmeong.feed.read.api.dto.client.CategoryDto;
import com.mulmeong.feed.read.api.dto.client.HashtagDto;
import com.mulmeong.feed.read.common.response.BaseResponse;
import java.util.List;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "member-service", url = "${api.member-service.url}")
public interface MemberClient {

    @GetMapping("/members/{memberUuid}/interests/categories")
    BaseResponse<List<CategoryDto>> getInterestCategory(@PathVariable String memberUuid);

    @GetMapping("/members/{memberUuid}/interests/hashtags")
    BaseResponse<List<HashtagDto>> getInterestHashtag(@PathVariable String memberUuid);

}
