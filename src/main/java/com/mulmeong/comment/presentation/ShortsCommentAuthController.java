package com.mulmeong.comment.presentation;

import com.mulmeong.comment.application.ShortsCommentService;
import com.mulmeong.comment.common.response.BaseResponse;
import com.mulmeong.comment.common.utils.CursorPage;
import com.mulmeong.comment.dto.in.ShortsCommentRequestDto;
import com.mulmeong.comment.dto.in.ShortsCommentUpdateDto;
import com.mulmeong.comment.dto.out.ShortsCommentResponseDto;
import com.mulmeong.comment.vo.in.ShortsCommentRequestVo;
import com.mulmeong.comment.vo.in.ShortsCommentUpdateVo;
import com.mulmeong.comment.vo.out.ShortsCommentResponseVo;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@RestController
@RequestMapping("/auth/v1/shorts")
public class ShortsCommentAuthController {

    private final ShortsCommentService shortsCommentService;

    @PostMapping("/{shortsUuid}/comments")
    @Operation(summary = "쇼츠 댓글 생성", tags = {"Shorts Comment Service"})
    public BaseResponse<Void> addShortsComment(
            @RequestBody ShortsCommentRequestVo requestVo,
            @PathVariable String shortsUuid) {
        shortsCommentService.createShortsComment(ShortsCommentRequestDto.toDto(requestVo, shortsUuid));
        return new BaseResponse<>();
    }

    @PutMapping("/comments/{commentUuid}")
    @Operation(summary = "쇼츠 댓글 수정", tags = {"Shorts Comment Service"})
    public BaseResponse<Void> updateShortsComment(
            @RequestHeader("Member-Uuid") String memberUuid,
            @RequestBody ShortsCommentUpdateVo updateVo,
            @PathVariable String commentUuid) {
        shortsCommentService.updateShortsComment(ShortsCommentUpdateDto.toDto(updateVo, commentUuid, memberUuid));
        return new BaseResponse<>();
    }

    @DeleteMapping("/comments/{commentUuid}")
    @Operation(summary = "쇼츠 댓글 삭제", tags = {"Shorts Comment Service"})
    public BaseResponse<Void> deleteShortsComment(
            @RequestHeader("Member-Uuid") String memberUuid,
            @PathVariable String commentUuid) {
        shortsCommentService.deleteShortsComment(memberUuid, commentUuid);
        return new BaseResponse<>();
    }

}
