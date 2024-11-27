package com.mulmeong.comment.presentation;

import com.mulmeong.comment.application.ShortsRecommentService;
import com.mulmeong.comment.common.response.BaseResponse;
import com.mulmeong.comment.common.utils.CursorPage;
import com.mulmeong.comment.dto.in.ShortsRecommentRequestDto;
import com.mulmeong.comment.dto.in.ShortsRecommentUpdateDto;
import com.mulmeong.comment.dto.out.ShortsRecommentResponseDto;
import com.mulmeong.comment.vo.in.ShortsRecommentRequestVo;
import com.mulmeong.comment.vo.in.ShortsRecommentUpdateVo;
import com.mulmeong.comment.vo.out.ShortsRecommentResponseVo;
import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@RestController
@RequestMapping("/auth/v1/shorts")
public class ShortsRecommentAuthController {
    private final ShortsRecommentService shortsRecommentService;

    @PostMapping("/comments/{commentUuid}/recomments")
    @Operation(summary = "쇼츠 대댓글 생성", tags = {"Shorts Recomment Service"})
    public BaseResponse<ShortsRecommentResponseVo> addShortsRecomment(
            @RequestBody ShortsRecommentRequestVo requestVo,
            @PathVariable String commentUuid) {

        return new BaseResponse<>(
                shortsRecommentService.createShortsRecomment(
                        ShortsRecommentRequestDto.toDto(requestVo, commentUuid)).toVo()
        );
    }

    @PutMapping("/comments/recomments/{recommentUuid}")
    @Operation(summary = "쇼츠 대댓글 수정", tags = {"Shorts Recomment Service"})
    public BaseResponse<Void> updateShortsRecomment(
            @RequestHeader("Member-Uuid") String memberUuid,
            @RequestBody ShortsRecommentUpdateVo updateVo,
            @PathVariable String recommentUuid) {
        shortsRecommentService.updateShortsRecomment(
                ShortsRecommentUpdateDto.toDto(updateVo, recommentUuid, memberUuid));
        return new BaseResponse<>();
    }

    @DeleteMapping("/comments/recomments/{recommentUuid}")
    @Operation(summary = "쇼츠 대댓글 삭제", tags = {"Shorts Recomment Service"})
    public BaseResponse<Void> deleteShortsRecomment(
            @RequestHeader("Member-Uuid") String memberUuid,
            @PathVariable String recommentUuid) {
        shortsRecommentService.deleteShortsRecomment(memberUuid, recommentUuid);
        return new BaseResponse<>();
    }

}
