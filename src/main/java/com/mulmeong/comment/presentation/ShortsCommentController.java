package com.mulmeong.comment.presentation;

import com.fasterxml.jackson.databind.ser.Serializers;
import com.mulmeong.comment.application.ShortsCommentService;
import com.mulmeong.comment.common.response.BaseResponse;
import com.mulmeong.comment.dto.in.ShortsCommentDeleteDto;
import com.mulmeong.comment.dto.in.ShortsCommentRequestDto;
import com.mulmeong.comment.dto.in.ShortsCommentUpdateDto;
import com.mulmeong.comment.vo.in.ShortsCommentRequestVo;
import com.mulmeong.comment.vo.in.ShortsCommentUpdateVo;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@RestController
@RequestMapping("v1/shorts")
public class ShortsCommentController {

    private final ShortsCommentService shortsCommentService;

    @PostMapping("/{shortsUuid}/comments")
    public BaseResponse<Void> addShortsComment(
            @RequestBody ShortsCommentRequestVo requestVo,
            @PathVariable String shortsUuid) {
        shortsCommentService.createFeedComment(ShortsCommentRequestDto.toDto(requestVo, shortsUuid));
        return new BaseResponse<>();
    }

    @PutMapping("/comments/{commentUuid}")
    public BaseResponse<Void> updateShortsComment(
            @RequestBody ShortsCommentUpdateVo updateVo,
            @PathVariable String commentUuid) {
        shortsCommentService.updateFeedComment(ShortsCommentUpdateDto.toDto(updateVo, commentUuid));
        return new BaseResponse<>();
    }

    @DeleteMapping("/comments/{commentUuid}")
    public BaseResponse<Void> deleteShortsComment(
            @PathVariable String commentUuid) {
        shortsCommentService.deleteShortsComment(commentUuid);
        return new BaseResponse<>();
    }
}
