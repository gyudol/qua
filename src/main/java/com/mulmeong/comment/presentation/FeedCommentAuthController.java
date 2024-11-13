package com.mulmeong.comment.presentation;

import com.mulmeong.comment.application.FeedCommentService;
import com.mulmeong.comment.common.response.BaseResponse;
import com.mulmeong.comment.common.utils.CursorPage;
import com.mulmeong.comment.dto.in.FeedCommentRequestDto;
import com.mulmeong.comment.dto.in.FeedCommentUpdateDto;
import com.mulmeong.comment.dto.out.FeedCommentResponseDto;
import com.mulmeong.comment.vo.in.FeedCommentRequestVo;
import com.mulmeong.comment.vo.in.FeedCommentUpdateVo;
import com.mulmeong.comment.vo.out.FeedCommentResponseVo;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@RestController
@Slf4j
@RequestMapping("/auth/v1/feeds")
public class FeedCommentAuthController {

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
            @RequestHeader("Member-Uuid") String memberUuid,
            @RequestBody FeedCommentUpdateVo updateVo,
            @PathVariable String commentUuid) {
        feedCommentService.updateFeedComment(FeedCommentUpdateDto.toDto(updateVo, commentUuid, memberUuid));
        return new BaseResponse<>();
    }

    @DeleteMapping("/comments/{commentUuid}")
    @Operation(summary = "피드 댓글 삭제", tags = {"Feed Comment Service"})
    public BaseResponse<Void> deleteFeedComment(
            @RequestHeader("Member-Uuid") String memberUuid,
            @PathVariable String commentUuid) {
        feedCommentService.deleteFeedComment(memberUuid, commentUuid);
        return new BaseResponse<>();
    }

}
