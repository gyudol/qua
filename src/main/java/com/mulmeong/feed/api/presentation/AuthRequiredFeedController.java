package com.mulmeong.feed.api.presentation;

import com.mulmeong.feed.api.application.FeedService;
import com.mulmeong.feed.api.dto.in.CreateFeedRequestDto;
import com.mulmeong.feed.api.vo.in.CreateFeedRequestVo;
import com.mulmeong.feed.common.response.BaseResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Tag(name = "Feed")
@RequiredArgsConstructor
@RequestMapping("/auth/v1/feeds")
@RestController
public class AuthRequiredFeedController {

    private final FeedService feedService;

    @Operation(summary = "Feed 생성 API")
    @PostMapping
    public BaseResponse<Void> createFeed(@RequestBody CreateFeedRequestVo requestVo) {

        feedService.createFeed(CreateFeedRequestDto.toDto(requestVo));
        return new BaseResponse<>();
    }

}
