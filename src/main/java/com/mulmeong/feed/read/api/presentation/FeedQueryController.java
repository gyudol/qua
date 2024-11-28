package com.mulmeong.feed.read.api.presentation;

import com.mulmeong.feed.read.api.application.FeedQueryService;
import com.mulmeong.feed.read.api.dto.FeedResponseDto;
import com.mulmeong.feed.read.common.response.BaseResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Tag(name = "Feed Query")
@RequiredArgsConstructor
@RequestMapping("/v1/feeds")
@RestController
public class FeedQueryController {

    private final FeedQueryService feedQueryService;

    @Operation(summary = "Feed 단건 정보 조회 API", description = "feedUuid 기준 **Feed Detail 조회**")
    @GetMapping("/{feedUuid}")
    public BaseResponse<FeedResponseDto> getSingleFeed(@PathVariable String feedUuid) {

        return new BaseResponse<>(feedQueryService.getSingleFeed(feedUuid));
    }

}
