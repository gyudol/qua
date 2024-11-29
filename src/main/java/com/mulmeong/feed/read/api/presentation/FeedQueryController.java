package com.mulmeong.feed.read.api.presentation;

import com.mulmeong.feed.read.api.application.FeedQueryService;
import com.mulmeong.feed.read.api.dto.in.FeedFilterRequestDto;
import com.mulmeong.feed.read.api.dto.out.FeedResponseDto;
import com.mulmeong.feed.read.common.response.BaseResponse;
import com.mulmeong.feed.read.common.utils.CursorPage;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@Tag(name = "Feed Query")
@RequiredArgsConstructor
@RequestMapping("/v1/feeds")
@RestController
@Slf4j
public class FeedQueryController {

    private final FeedQueryService feedQueryService;

    @Operation(summary = "Feed 단건 정보 조회 API", description = "feedUuid 기준 **Feed Detail 조회**")
    @GetMapping("/{feedUuid}")
    public BaseResponse<FeedResponseDto> getSingleFeed(@PathVariable String feedUuid) {

        return new BaseResponse<>(feedQueryService.getSingleFeed(feedUuid));
    }

    @Operation(summary = "(특정 카테고리 / 특정 해시태그)의 (최신순 / 좋아요순) 피드 목록 조회 API",
        description = "sortBy: `LATEST` / `LIKES` (대·소문자 구분하지 않음)<br><br>"
                      + "LATEST: **최신순**, LIKES: **netLikes 내림차순** (likesCount - dislikeCount)")
    @GetMapping
    public BaseResponse<CursorPage<FeedResponseDto>> getFeedsByCategoryOrTag(
        @RequestParam(required = false) String categoryName,
        @RequestParam(required = false) String hashtagName,
        @RequestParam(defaultValue = "latest", required = false) String sortBy,
        @RequestParam(required = false, name = "nextCursor") String lastId,
        @RequestParam(required = false) Integer pageSize,
        @RequestParam(required = false) Integer pageNo) {

        return new BaseResponse<>(
            feedQueryService.getFeedsByCategoryOrTag(FeedFilterRequestDto.toDto(
                categoryName, hashtagName, sortBy, lastId, pageSize, pageNo)));
    }

}
