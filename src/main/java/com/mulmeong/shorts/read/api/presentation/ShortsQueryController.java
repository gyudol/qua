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
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@Tag(name = "Shorts Query")
@RequiredArgsConstructor
@RequestMapping("/v1")
@RestController
public class ShortsQueryController {

    private final ShortsQueryService shortsQueryService;

    @Operation(summary = "Shorts 단건 정보 조회 API", description = """
        - Visibility: `VISIBLE` / `HIDDEN` / `REPORTED`<br>
        - MediaType: `VIDEO_THUMBNAIL` / `VIDEO_360` / `VIDEO_540` / `VIDEO_720` / `VIDEO_MP4`<br><br>
        - playtime: second 기준으로 입력 ex) 90 sec => 90 저장 **(Range: 0 ~ 32767)**
        - ShortsMedia 테이블은 **FE에서 생성한 MediaUUID를 기본키로 설정**하는 것에 주의""")
    @GetMapping("/shorts/{shortsUuid}")
    public BaseResponse<ShortsResponseDto> getSingleShorts(@PathVariable String shortsUuid) {

        return new BaseResponse<>(shortsQueryService.getSingleShorts(shortsUuid));
    }

    @Operation(summary = "특정 사용자가 작성한 Shorts 목록 조회 API (Guest가 요청시)",
        description = """
            - Visibility = `VISIBLE`인 Shorts만을 조회<br><br>
            - sortBy: `LATEST` / `LIKES` (대·소문자 구분하지 않음)<br><br>
            - LATEST: **최신순**, LIKES: **netLikes 내림차순** (likesCount - dislikeCount)""")
    @GetMapping("/members/{memberUuid}/shorts")
    public BaseResponse<CursorPage<ShortsResponseDto>> getShortsByAuthor(
        @PathVariable(name = "memberUuid") String authorUuid,
        @RequestParam(defaultValue = "latest", required = false) String sortBy,
        @RequestParam(required = false, name = "nextCursor") String lastId,
        @RequestParam(required = false) Integer pageSize,
        @RequestParam(required = false) Integer pageNo) {

        return new BaseResponse<>(shortsQueryService.getShortsByAuthor(ShortsAuthorRequestDto.toDto(
            authorUuid, false, sortBy, lastId, pageSize, pageNo)));
    }

    @Operation(summary = "비회원에게 추천되는 Shorts 목록 조회 API", description = """
        - netLikes 내림차순 조회<br><br>
        - Visibility: `VISIBLE`인 쇼츠 목록만 조회
        """)
    @GetMapping("/shorts/recommendation")
    public BaseResponse<CursorPage<ShortsResponseDto>> getRecommendedShortsForGuest(
        @RequestParam(required = false, name = "nextCursor") String lastId,
        @RequestParam(required = false) Integer pageSize,
        @RequestParam(required = false) Integer pageNo) {

        return new BaseResponse<>(shortsQueryService.getRecommendedShorts(
            ShortsRecommendationRequestDto.toDto(null, false, lastId, pageSize, pageNo)));
    }

}
