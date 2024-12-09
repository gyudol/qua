package com.mulmeong.feed.read.api.presentation;

import com.mulmeong.feed.read.api.application.FeedQueryService;
import com.mulmeong.feed.read.api.dto.in.FeedAuthorRequestDto;
import com.mulmeong.feed.read.api.dto.out.FeedResponseDto;
import com.mulmeong.feed.read.common.response.BaseResponse;
import com.mulmeong.feed.read.common.utils.CursorPage;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@Tag(name = "Auth Required Feed")
@RequiredArgsConstructor
@RequestMapping("/auth/v1/members")
@RestController
public class AuthRequiredFeedQueryController {

    private final FeedQueryService feedQueryService;

    @Operation(summary = "특정 사용자가 작성한 피드 목록 조회 (프로필 페이지의 Owner가 요청시)",
        description = """
            - 조건에 부합하는 모든 Feed를 조회<br><br>
            - sortBy: `LATEST` / `LIKES` (대·소문자 구분하지 않음)<br><br>
            - LATEST: **최신순**, LIKES: **netLikes 내림차순** (likesCount - dislikeCount)""")
    @GetMapping("/{memberUuid}/feeds")
    public BaseResponse<CursorPage<FeedResponseDto>> getFeedsByAuthor(
        @PathVariable(name = "memberUuid") String authorUuid,
        @RequestParam(defaultValue = "latest", required = false) String sortBy,
        @RequestParam(required = false, name = "nextCursor") String lastId,
        @RequestParam(required = false) Integer pageSize,
        @RequestParam(required = false) Integer pageNo) {

        return new BaseResponse<>(feedQueryService.getFeedsByAuthor(FeedAuthorRequestDto.toDto(
            authorUuid, true, sortBy, lastId, pageSize, pageNo)));
    }
}
