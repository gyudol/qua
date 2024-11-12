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
@RequestMapping("/v1/feeds")
public class FeedRecommentController {
    private final FeedRecommentService feedRecommentService;

    @PostMapping("/comments/{commentUuid}/recomments")
    @Operation(summary = "피드 대댓글 추가", tags = {"Feed Recomment Service"})
    public BaseResponse<Void> addFeedRecomment(
            @RequestBody FeedRecommentRequestVo requestVo,
            @PathVariable String commentUuid) {
        feedRecommentService.createFeedComment(FeedRecommentRequestDto.toDto(requestVo, commentUuid));
        return new BaseResponse<>();
    }

    @PutMapping("/comments/recomments/{recommentUuid}")
    @Operation(summary = "피드 대댓글 수정", tags = {"Feed Recomment Service"})
    public BaseResponse<Void> updateFeedRecomment(
            @RequestBody FeedRecommentUpdateVo updateVo,
            @PathVariable String recommentUuid) {
        feedRecommentService.updateFeedComment(FeedRecommentUpdateDto.toDto(updateVo, recommentUuid));
        return new BaseResponse<>();
    }

    @DeleteMapping("/comments/recomments/{recommentUuid}")
    @Operation(summary = "피드 대댓글 삭제", tags = {"Feed Recomment Service"})
    public BaseResponse<Void> deleteFeedRecomment(@PathVariable String recommentUuid) {
        feedRecommentService.deleteFeedComment(recommentUuid);
        return new BaseResponse<>();
    }

    @GetMapping("/comments/recomments/{recommentUuid}")
    @Operation(summary = "피드 대댓글 단건 조회", tags = {"Feed Recomment Service"})
    public BaseResponse<FeedRecommentResponseVo> getFeedRecomment(@PathVariable String recommentUuid) {
        return new BaseResponse<>(feedRecommentService.getFeedRecomment(recommentUuid).toVo());
    }

    @GetMapping("/comments/{commentUuid}/recomments")
    @Operation(summary = "피드 대댓글 페이지 조회", tags = {"Feed Recomment Service"})
    public BaseResponse<CursorPage<FeedRecommentResponseVo>> findFeedRecomments(
            @PathVariable String commentUuid,
            @RequestParam(value = "nextCursor", required = false) Long lastId,
            @RequestParam(value = "pageSize", required = false) Integer pageSize,
            @RequestParam(value = "pageNo", required = false) Integer pageNo) {

        CursorPage<FeedRecommentResponseDto> cursorPage = feedRecommentService
                .getFeedRecomments(commentUuid, lastId, pageSize, pageNo);

        return new BaseResponse<>(CursorPage.toCursorPage(cursorPage, cursorPage.getContent().stream()
                .map(FeedRecommentResponseDto::toVo).toList()));
    }
}
