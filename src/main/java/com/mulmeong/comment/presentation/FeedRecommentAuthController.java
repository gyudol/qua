package com.mulmeong.comment.presentation;

import com.mulmeong.comment.application.FeedRecommentService;
import com.mulmeong.comment.common.response.BaseResponse;
import com.mulmeong.comment.common.utils.CursorPage;
import com.mulmeong.comment.dto.in.FeedRecommentRequestDto;
import com.mulmeong.comment.dto.in.FeedRecommentUpdateDto;
import com.mulmeong.comment.dto.out.FeedRecommentResponseDto;
import com.mulmeong.comment.vo.in.FeedRecommentRequestVo;
import com.mulmeong.comment.vo.in.FeedRecommentUpdateVo;
import com.mulmeong.comment.vo.out.FeedRecommentResponseVo;
import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;


@RequiredArgsConstructor
@RestController
@RequestMapping("/auth/v1/feeds")
public class FeedRecommentAuthController {
    private final FeedRecommentService feedRecommentService;

    @PostMapping("/comments/{commentUuid}/recomments")
    @Operation(summary = "피드 대댓글 생성", tags = {"Feed Recomment Service"})
    public BaseResponse<FeedRecommentResponseVo> addFeedRecomment(
            @RequestBody FeedRecommentRequestVo requestVo,
            @PathVariable String commentUuid) {
        return new BaseResponse<>(
                feedRecommentService.createFeedRecomment(FeedRecommentRequestDto.toDto(requestVo, commentUuid)).toVo()
        );
    }

    @PutMapping("/comments/recomments/{recommentUuid}")
    @Operation(summary = "피드 대댓글 수정", tags = {"Feed Recomment Service"})
    public BaseResponse<Void> updateFeedRecomment(
            @RequestHeader("Member-Uuid") String memberUuid,
            @RequestBody FeedRecommentUpdateVo updateVo,
            @PathVariable String recommentUuid) {
        feedRecommentService.updateFeedRecomment(FeedRecommentUpdateDto.toDto(updateVo, recommentUuid, memberUuid));
        return new BaseResponse<>();
    }

    @DeleteMapping("/comments/recomments/{recommentUuid}")
    @Operation(summary = "피드 대댓글 삭제", tags = {"Feed Recomment Service"})
    public BaseResponse<Void> deleteFeedRecomment(
            @RequestHeader("Member-Uuid") String memberUuid,
            @PathVariable String recommentUuid) {
        feedRecommentService.deleteFeedRecomment(memberUuid, recommentUuid);
        return new BaseResponse<>();
    }

}
