package com.mulmeong.feed.api.presentation;

import com.mulmeong.feed.api.application.FeedService;
import com.mulmeong.feed.api.dto.in.CreateFeedRequestDto;
import com.mulmeong.feed.api.dto.in.UpdateFeedRequestDto;
import com.mulmeong.feed.api.dto.in.UpdateFeedStatusRequestDto;
import com.mulmeong.feed.api.vo.in.CreateFeedRequestVo;
import com.mulmeong.feed.api.vo.in.UpdateFeedRequestVo;
import com.mulmeong.feed.api.vo.in.UpdateFeedStatusRequestVo;
import com.mulmeong.feed.common.response.BaseResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Tag(name = "Feed")
@RequiredArgsConstructor
@RequestMapping("/auth/v1/feeds")
@RestController
public class AuthRequiredFeedController {

    private final FeedService feedService;

    @Operation(summary = "Feed 생성 API", description = "MediaType: `IMAGE / VIDEO`, "
        + "MediaSubType: `IMAGE / VIDEO_THUMBNAIL / VIDEO_360 / VIDEO_540 / VIDEO_720 / VIDEO_MP4`")
    @PostMapping
    public BaseResponse<Void> createFeed(@RequestBody CreateFeedRequestVo requestVo) {

        feedService.createFeed(CreateFeedRequestDto.toDto(requestVo));
        return new BaseResponse<>();
    }

    @Operation(summary = "Feed 수정 API", description = "Visibility 상태 수정은 **Feed Visibility 상태 수정 API**에 요청 필요")
    @PutMapping("/{feedUuid}")
    public BaseResponse<Void> updateFeed(
        @PathVariable String feedUuid, @RequestBody UpdateFeedRequestVo requestVo) {

        feedService.updateFeed(UpdateFeedRequestDto.toDto(requestVo, feedUuid));
        return new BaseResponse<>();
    }

    @Operation(summary = "Feed Visibility 상태 수정 API", description = "Visibility: `VISIBLE / HIDDEN / REPORTED`")
    @PutMapping("/{feedUuid}/feed-status")
    public BaseResponse<Void> updateFeedStatus(
        @PathVariable String feedUuid, @RequestBody UpdateFeedStatusRequestVo requestVo) {

        feedService.updateFeedStatus(UpdateFeedStatusRequestDto.toDto(requestVo, feedUuid));
        return new BaseResponse<>();
    }

    @Operation(summary = "Feed 삭제 API")
    @DeleteMapping("/{feedUuid}")
    public BaseResponse<Void> deleteFeed(@PathVariable String feedUuid) {

        feedService.deleteFeed(feedUuid);
        return new BaseResponse<>();
    }

}
