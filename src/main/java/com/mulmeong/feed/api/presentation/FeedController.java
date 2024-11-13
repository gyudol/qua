package com.mulmeong.feed.api.presentation;

import com.mulmeong.feed.api.application.FeedService;
import com.mulmeong.feed.api.dto.out.FeedResponseDto;
import com.mulmeong.feed.common.response.BaseResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Tag(name = "Feed")
@RequiredArgsConstructor
@RequestMapping("/v1/feeds")
@RestController
public class FeedController {

    private final FeedService feedService;

    @Operation(summary = "Feed 단건 정보 조회 API", description = "feedUuid 기준 **Feed Detail 조회**")
    @GetMapping("/{feedUuid}")
    public BaseResponse<FeedResponseDto> getFeedDetail(@PathVariable("feedUuid") String feedUuid) {

        return new BaseResponse<>(feedService.getFeedDetail(feedUuid));
    }

}
