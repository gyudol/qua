package com.mulmeong.comment.presentation;

import com.fasterxml.jackson.databind.ser.Serializers;
import com.mulmeong.comment.application.ShortsRecommentService;
import com.mulmeong.comment.common.response.BaseResponse;
import com.mulmeong.comment.common.utils.CursorPage;
import com.mulmeong.comment.dto.in.ShortsRecommentRequestDto;
import com.mulmeong.comment.dto.in.ShortsRecommentUpdateDto;
import com.mulmeong.comment.dto.out.ShortsRecommentResponseDto;
import com.mulmeong.comment.vo.in.ShortsRecommentRequestVo;
import com.mulmeong.comment.vo.in.ShortsRecommentUpdateVo;
import com.mulmeong.comment.vo.out.ShortsRecommentResponseVo;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.awt.*;
import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("/v1/shorts")
public class ShortsRecommentController {
    private final ShortsRecommentService shortsRecommentService;

    @PostMapping("/comments/{commentUuid}/recomments")
    @Operation(summary = "쇼츠 대댓글 추가", tags = {"Shorts Comment Service"})
    public BaseResponse<Void> addShortsRecomment(
            @RequestBody ShortsRecommentRequestVo requestVo,
            @PathVariable String commentUuid) {
        shortsRecommentService.createShortsRecomment(ShortsRecommentRequestDto.toDto(requestVo, commentUuid));
        return new BaseResponse<>();
    }

    @PutMapping("/comments/recomments/{recommentUuid}")
    @Operation(summary = "쇼츠 대댓글 수정", tags = {"Shorts Comment Service"})
    public BaseResponse<Void> updateShortsRecomment(
            @RequestBody ShortsRecommentUpdateVo updateVo,
            @PathVariable String recommentUuid) {
        shortsRecommentService.updateShortsRecomment(ShortsRecommentUpdateDto.toDto(updateVo, recommentUuid));
        return new BaseResponse<>();
    }

    @DeleteMapping("/comments/recomments/{recommentUuid}")
    @Operation(summary = "쇼츠 대댓글 삭제", tags = {"Shorts Comment Service"})
    public BaseResponse<Void> deleteShortsRecomment(@PathVariable String recommentUuid) {
        shortsRecommentService.deleteShortsRecomment(recommentUuid);
        return new BaseResponse<>();
    }

    @GetMapping("/comments/{commentUuid}/recomments")
    @Operation(summary = "쇼츠 대댓글 최신순, 추천순 조회", tags = {"Shorts Comment Service"})
    public BaseResponse<CursorPage<ShortsRecommentResponseVo>> findShortsRecomment(
            @PathVariable String commentUuid,
            @Parameter(
                    description = "정렬 기준",
                    schema = @Schema(allowableValues = {"latest", "likes"})
            )
            @RequestParam(value = "sortBy") String sortBy,
            @RequestParam(value = "lastId", required = false) Long lastId,
            @RequestParam(value = "pageSize", required = false) Integer pageSize,
            @RequestParam(value = "pageNo", required = false) Integer pageNo
    ) {
        CursorPage<ShortsRecommentResponseDto> cursorPage = shortsRecommentService.getShortsRecommets(
                commentUuid, sortBy, lastId, pageSize, pageNo);

        return new BaseResponse<>(CursorPage.toCursorPage(cursorPage, cursorPage.getContent().stream()
                .map(ShortsRecommentResponseDto::toVo).toList()));
    }

}
