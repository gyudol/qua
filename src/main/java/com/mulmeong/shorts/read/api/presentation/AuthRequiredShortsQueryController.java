package com.mulmeong.shorts.read.api.presentation;

import com.mulmeong.shorts.read.api.application.ShortsQueryService;
import com.mulmeong.shorts.read.api.dto.in.ShortsAuthorRequestDto;
import com.mulmeong.shorts.read.api.dto.in.ShortsRecommendationRequestDto;
import com.mulmeong.shorts.read.api.dto.out.ShortsResponseDto;
import com.mulmeong.shorts.read.common.response.BaseResponse;
import com.mulmeong.shorts.read.common.utils.CursorPage;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@Tag(name = "Auth Required Shorts")
@RequiredArgsConstructor
@RequestMapping("/auth/v1/members/{memberUuid}/shorts")
@RestController
public class AuthRequiredShortsQueryController {

    private final ShortsQueryService shortsQueryService;

    @Operation(summary = "특정 사용자가 작성한 Shorts 목록 조회 API",
        description = """
            - **요청자와 작성자의 memberUuid가 같으면** 조건에 맞는 모든 Shorts를,
            같지 않으면 Visibility = `VISIBLE`인 Shorts만을 조회<br><br>
            - 작성자: **Path Parameter**, 요청자: Member-Uuid in **Header**<br><br>
            - sortBy: `LATEST` / `LIKES` (대·소문자 구분하지 않음)<br><br>
            - LATEST: **최신순**, LIKES: **netLikes 내림차순** (likesCount - dislikeCount)""")
    @GetMapping
    public BaseResponse<CursorPage<ShortsResponseDto>> getShortsByAuthor(
        @PathVariable(name = "memberUuid") String authorUuid,
        @RequestHeader("Member-Uuid") String requesterUuid,
        @RequestParam(defaultValue = "latest", required = false) String sortBy,
        @RequestParam(required = false, name = "nextCursor") String lastId,
        @RequestParam(required = false) Integer pageSize,
        @RequestParam(required = false) Integer pageNo) {

        return new BaseResponse<>(shortsQueryService.getShortsByAuthor(ShortsAuthorRequestDto.toDto(
            authorUuid, requesterUuid, sortBy, lastId, pageSize, pageNo)));
    }

    @Operation(summary = "특정 사용자에게 추천되는 쇼츠 목록 조회 API", description = "(임시) Visibility: `VISIBLE`인 쇼츠 목록만 조회")
    @GetMapping("/recommendation")
    public BaseResponse<CursorPage<ShortsResponseDto>> getRecommendedShortsForMember(
        @PathVariable String memberUuid,
        @RequestParam(required = false, name = "nextCursor") String lastId,
        @RequestParam(required = false) Integer pageSize,
        @RequestParam(required = false) Integer pageNo) {

        return new BaseResponse<>(shortsQueryService.getRecommendedShorts(
            ShortsRecommendationRequestDto.toDto(memberUuid, true, lastId, pageSize, pageNo)));
    }

}
