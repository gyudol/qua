package com.mulmeong.feed.api.presentation;

import com.mulmeong.feed.api.application.FeedService;
import com.mulmeong.feed.api.dto.in.FeedCreateDto;
import com.mulmeong.feed.api.dto.in.FeedHashtagUpdateDto;
import com.mulmeong.feed.api.dto.in.FeedUpdateDto;
import com.mulmeong.feed.api.dto.in.FeedStatusUpdateDto;
import com.mulmeong.feed.api.vo.in.FeedCreateVo;
import com.mulmeong.feed.api.vo.in.FeedHashtagUpdateVo;
import com.mulmeong.feed.api.vo.in.FeedUpdateVo;
import com.mulmeong.feed.api.vo.in.FeedStatusUpdateVo;
import com.mulmeong.feed.common.response.BaseResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
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

    @Operation(summary = "Feed 생성 API", description = """
        - Visibility: `VISIBLE` / `HIDDEN` / `REPORTED`<br>
        - MediaType: `IMAGE` / `VIDEO`<br>
        - MediaSubType: `IMAGE` / `VIDEO_THUMBNAIL` / `VIDEO_360` / `VIDEO_540` / `VIDEO_720` / `VIDEO_MP4`<br><br>
        - FeedMedia 테이블은 **FE에서 생성한 MediaUUID를 기본키로 설정**하는 것에 주의""")
    @PostMapping
    public BaseResponse<Void> createFeed(@RequestBody @Valid FeedCreateVo requestVo) {

        feedService.createFeed(FeedCreateDto.toDto(requestVo));
        return new BaseResponse<>();
    }

    @Operation(summary = "Feed 수정 API")
    @PutMapping("/{feedUuid}")
    public BaseResponse<Void> updateFeed(
        @PathVariable String feedUuid, @RequestBody FeedUpdateVo requestVo) {

        feedService.updateFeed(FeedUpdateDto.toDto(requestVo, feedUuid));
        return new BaseResponse<>();
    }

    @Operation(summary = "Feed Visibility 상태 수정 API", description = "Visibility: `VISIBLE` / `HIDDEN` / `REPORTED`")
    @PutMapping("/{feedUuid}/status")
    public BaseResponse<Void> updateFeedStatus(
        @PathVariable String feedUuid, @RequestBody FeedStatusUpdateVo requestVo) {

        feedService.updateFeedStatus(FeedStatusUpdateDto.toDto(requestVo, feedUuid));
        return new BaseResponse<>();
    }

    @Operation(summary = "Feed Hashtag 수정 API")
    @PutMapping("/{feedUuid}/hashtags")
    public BaseResponse<Void> updateFeedHashtag(
        @PathVariable String feedUuid, @RequestBody @Valid FeedHashtagUpdateVo requestVo) {

        feedService.updateFeedHashtag(FeedHashtagUpdateDto.toDto(requestVo, feedUuid));
        return new BaseResponse<>();
    }

    @Operation(summary = "Feed 삭제 API")
    @DeleteMapping("/{feedUuid}")
    public BaseResponse<Void> deleteFeed(@PathVariable String feedUuid) {

        feedService.deleteFeed(feedUuid);
        return new BaseResponse<>();
    }

}
