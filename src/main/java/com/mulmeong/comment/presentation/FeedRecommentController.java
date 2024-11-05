package com.mulmeong.comment.presentation;

import com.mulmeong.comment.application.FeedRecommentService;
import com.mulmeong.comment.common.response.BaseResponse;
import com.mulmeong.comment.dto.in.FeedRecommentRequestDto;
import com.mulmeong.comment.dto.in.FeedRecommentUpdateDto;
import com.mulmeong.comment.vo.in.FeedRecommentRequestVo;
import com.mulmeong.comment.vo.in.FeedRecommentUpdateVo;
import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@RestController
@RequestMapping("/v1/feeds")
public class FeedRecommentController {
    private final FeedRecommentService feedRecommentService;

    @PostMapping("/comments/{commentUuid}/recomments")
    @Operation(summary = "피드 대댓글 추가", tags = {"Feed Comment Service"})
    public BaseResponse<Void> addFeedRecomment(
            @RequestBody FeedRecommentRequestVo requestVo,
            @PathVariable String commentUuid) {
        feedRecommentService.createFeedComment(FeedRecommentRequestDto.toDto(requestVo, commentUuid));
        return new BaseResponse<>();
    }

    @PutMapping("/comments/recomments/{recommentUuid}")
    @Operation(summary = "피드 대댓글 수정", tags = {"Feed Comment Service"})
    public BaseResponse<Void> updateFeedRecomment(
            @RequestBody FeedRecommentUpdateVo updateVo,
            @PathVariable String recommentUuid) {
        feedRecommentService.updateFeedComment(FeedRecommentUpdateDto.toDto(updateVo, recommentUuid));
        return new BaseResponse<>();
    }

    @DeleteMapping("/comments/recomments/{recommentUuid}")
    @Operation(summary = "피드 대댓글 삭제", tags = {"Feed Comment Service"})
    public BaseResponse<Void> deleteFeedRecomment(@PathVariable String recommentUuid) {
        feedRecommentService.deleteFeedComment(recommentUuid);
        return new BaseResponse<>();
    }

}
