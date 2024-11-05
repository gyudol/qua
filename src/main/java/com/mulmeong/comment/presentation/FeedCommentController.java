package com.mulmeong.comment.presentation;

import com.mulmeong.comment.application.FeedCommentService;
import com.mulmeong.comment.common.response.BaseResponse;
import com.mulmeong.comment.dto.in.FeedCommentRequestDto;
import com.mulmeong.comment.dto.in.FeedCommentUpdateDto;
import com.mulmeong.comment.vo.in.FeedCommentRequestVo;
import com.mulmeong.comment.vo.in.FeedCommentUpdateVo;
import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@RestController
@RequestMapping("/v1/feeds")
public class FeedCommentController {

    private final FeedCommentService feedCommentService;

    @PostMapping("{feedUuid}/comments")
    @Operation(summary = "피드 댓글 추가", tags = {"Feed Comment Service"})
    public BaseResponse<Void> addFeedComment(
            @RequestBody FeedCommentRequestVo requestVo,
            @PathVariable String feedUuid) {
        feedCommentService.createFeedComment(FeedCommentRequestDto.toDto(requestVo, feedUuid));
        return new BaseResponse<>();
    }

    @PutMapping("/comments/{commentUuid}")
    @Operation(summary = "피드 댓글 수정", tags = {"Feed Comment Service"})
    public BaseResponse<Void> updateFeedComment(
            @RequestBody FeedCommentUpdateVo updateVo,
            @PathVariable String commentUuid) {
        feedCommentService.updateFeedComment(FeedCommentUpdateDto.toDto(updateVo, commentUuid));
        return new BaseResponse<>();
    }

    @DeleteMapping("/comments/{commentUuid}")
    @Operation(summary = "피드 댓글 삭제", tags = {"Feed Comment Service"})
    public BaseResponse<Void> deleteFeedComment(@PathVariable String commentUuid) {
        feedCommentService.deleteFeedComment(commentUuid);
        return new BaseResponse<>();
    }

}
