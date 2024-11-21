package com.mulmeong.comment.read.presentation;

import com.mulmeong.comment.read.application.ShortsCommentService;
import com.mulmeong.comment.read.common.response.BaseResponse;
import com.mulmeong.comment.read.common.utils.CursorPage;
import com.mulmeong.comment.read.dto.out.ShortsCommentResponseDto;
import com.mulmeong.comment.read.vo.ShortsCommentResponseVo;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@RestController
@RequestMapping("v1/shorts")
public class ShortsCommentController {

    private final ShortsCommentService shortsCommentService;

    @GetMapping("/{shortsUuid}/comments")
    @Operation(summary = "쇼츠 댓글 최신순 , 추천순 조회", tags = {"Shorts Comment Service"})
    public BaseResponse<CursorPage<ShortsCommentResponseVo>> getShortsCommentsByPage(
            @PathVariable String shortsUuid,
            @Parameter(
                    description = "정렬 기준",
                    schema = @Schema(allowableValues = {"latest", "likes"})
            )
            @RequestParam(defaultValue = "latest", value = "sortBy", required = false) String sortBy,
            @RequestParam(value = "nextCursor", required = false) String lastId,
            @RequestParam(value = "pageSize", required = false) Integer pageSize,
            @RequestParam(value = "pageNo", required = false) Integer pageNo
    ) {
        CursorPage<ShortsCommentResponseDto> cursorPage = shortsCommentService.getShortsCommentsByPage(
                shortsUuid, sortBy, lastId, pageSize, pageNo);
        return new BaseResponse<>(
                CursorPage.toCursorPage(cursorPage, cursorPage.getContent().stream()
                        .map(ShortsCommentResponseDto::toVo).toList()));
    }
}
