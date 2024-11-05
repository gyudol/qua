package com.mulmeong.comment.presentation;

import com.mulmeong.comment.application.ShortsRecommentService;
import com.mulmeong.comment.common.response.BaseResponse;
import com.mulmeong.comment.dto.in.ShortsRecommentRequestDto;
import com.mulmeong.comment.dto.in.ShortsRecommentUpdateDto;
import com.mulmeong.comment.vo.in.ShortsRecommentRequestVo;
import com.mulmeong.comment.vo.in.ShortsRecommentUpdateVo;
import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@RestController
@RequestMapping("/v1/shorts")
public class ShortsRecommentController {
    private final ShortsRecommentService shortsRecommentService;

    @PostMapping("/comments/{commentUuid}/recomments")
    @Operation(summary = "쇼츠 대댓글 추가", tags = {"Shorts Comment Service"})
    public BaseResponse<Void> addFeedRecomment(
            @RequestBody ShortsRecommentRequestVo requestVo,
            @PathVariable String commentUuid) {
        shortsRecommentService.createFeedComment(ShortsRecommentRequestDto.toDto(requestVo, commentUuid));
        return new BaseResponse<>();
    }

    @PutMapping("/comments/recomments/{recommentUuid}")
    @Operation(summary = "쇼츠 대댓글 수정", tags = {"Shorts Comment Service"})
    public BaseResponse<Void> updateFeedRecomment(
            @RequestBody ShortsRecommentUpdateVo updateVo,
            @PathVariable String recommentUuid) {
        shortsRecommentService.updateFeedComment(ShortsRecommentUpdateDto.toDto(updateVo, recommentUuid));
        return new BaseResponse<>();
    }

    @DeleteMapping("/comments/recomments/{recommentUuid}")
    @Operation(summary = "쇼츠 대댓글 삭제", tags = {"Shorts Comment Service"})
    public BaseResponse<Void> deleteFeedRecomment(@PathVariable String recommentUuid) {
        shortsRecommentService.deleteFeedComment(recommentUuid);
        return new BaseResponse<>();
    }
}
