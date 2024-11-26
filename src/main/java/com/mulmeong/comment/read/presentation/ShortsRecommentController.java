package com.mulmeong.comment.read.presentation;

import com.mulmeong.comment.read.application.ShortsRecommentService;
import com.mulmeong.comment.read.common.response.BaseResponse;
import com.mulmeong.comment.read.common.utils.CursorPage;
import com.mulmeong.comment.read.dto.out.ShortsRecommentResponseDto;
import com.mulmeong.comment.read.vo.ShortsRecommentResponseVo;
import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@RestController
@RequestMapping("/v1/shorts")
public class ShortsRecommentController {
    private final ShortsRecommentService shortsRecommentService;

    @GetMapping("/comments/{commentUuid}/recomments")
    @Operation(summary = "쇼츠 대댓글 페이지 조회", tags = {"Shorts Recomment Service"})
    public BaseResponse<CursorPage<ShortsRecommentResponseVo>> findShortsRecomment(
            @PathVariable String commentUuid,
            @RequestParam(value = "nextCursor", required = false) String lastId,
            @RequestParam(value = "pageSize", required = false) Integer pageSize,
            @RequestParam(value = "pageNo", required = false) Integer pageNo
    ) {
        CursorPage<ShortsRecommentResponseDto> cursorPage = shortsRecommentService.getShortsRecomments(
                commentUuid, lastId, pageSize, pageNo);

        return new BaseResponse<>(CursorPage.toCursorPage(cursorPage, cursorPage.getContent().stream()
                .map(ShortsRecommentResponseDto::toVo).toList()));
    }

}
