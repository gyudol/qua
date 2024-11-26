package com.mulmeong.comment.read.presentation;

import com.mulmeong.comment.read.application.FeedRecommentService;
import com.mulmeong.comment.read.common.response.BaseResponse;
import com.mulmeong.comment.read.common.utils.CursorPage;
import com.mulmeong.comment.read.dto.out.FeedRecommentResponseDto;
import com.mulmeong.comment.read.vo.FeedRecommentResponseVo;
import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;


@RequiredArgsConstructor
@RestController
@RequestMapping("/v1/feeds")
public class FeedRecommentController {
    private final FeedRecommentService feedRecommentService;

    @GetMapping("/comments/{commentUuid}/recomments")
    @Operation(summary = "피드 대댓글 페이지 조회", tags = {"Feed Recomment Service"})
    public BaseResponse<CursorPage<FeedRecommentResponseVo>> findFeedRecomments(
            @PathVariable String commentUuid,
            @RequestParam(value = "nextCursor", required = false) String lastId,
            @RequestParam(value = "pageSize", required = false) Integer pageSize,
            @RequestParam(value = "pageNo", required = false) Integer pageNo) {

        CursorPage<FeedRecommentResponseDto> cursorPage = feedRecommentService
                .getFeedRecomments(commentUuid, lastId, pageSize, pageNo);

        return new BaseResponse<>(CursorPage.toCursorPage(cursorPage, cursorPage.getContent().stream()
                .map(FeedRecommentResponseDto::toVo).toList()));
    }
}
