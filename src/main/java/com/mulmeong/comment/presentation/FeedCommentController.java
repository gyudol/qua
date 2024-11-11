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
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.repository.query.Param;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@RestController
@Slf4j
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

    @GetMapping("/comments")
    @Operation(summary = "피드 댓글 단건 조회", tags = {"Feed Comment Service"})
    public BaseResponse<FeedCommentResponseVo> getFeedComment(@RequestParam String commentUuid) {

        return new BaseResponse<>(feedCommentService.getFeedComment(commentUuid).toVo());
    }

    @GetMapping("/{feedUuid}/comments")
    @Operation(summary = "피드 댓글 최신순 , 추천순 조회", tags = {"Feed Comment Service"})
    public BaseResponse<CursorPage<FeedCommentResponseVo>> getFeedCommentsByPage(
            @PathVariable String feedUuid,
            @Parameter(
                    description = "정렬 기준",
                    schema = @Schema(allowableValues = {"latest", "likes"})
            )
            @RequestParam(value = "sortBy") String sortBy,
            @RequestParam(value = "lastId", required = false) Long lastId,
            @RequestParam(value = "pageSize", required = false) Integer pageSize,
            @RequestParam(value = "pageNo", required = false) Integer pageNo
    ) {
        CursorPage<FeedCommentResponseDto> cursorPage = feedCommentService.getFeedCommentsByPage(
                feedUuid, sortBy, lastId, pageSize, pageNo);
        return new BaseResponse<>(
                CursorPage.toCursorPage(cursorPage, cursorPage.getContent().stream()
                        .map(FeedCommentResponseDto::toVo).toList())
        );

    }
}
