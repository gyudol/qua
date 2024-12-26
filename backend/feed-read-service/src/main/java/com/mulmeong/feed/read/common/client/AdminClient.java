package com.mulmeong.feed.read.common.client;

import com.mulmeong.feed.read.api.dto.client.CategoryVo;
import com.mulmeong.feed.read.common.response.BaseResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "admin-service", url = "${api.admin-service.url}")
public interface AdminClient {

    @GetMapping("/category/{categoryId}")
    BaseResponse<CategoryVo> getCategory(@PathVariable Long categoryId);

}
