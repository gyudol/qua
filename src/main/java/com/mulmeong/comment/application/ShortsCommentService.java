package com.mulmeong.comment.application;

import com.mulmeong.comment.common.utils.CursorPage;
import com.mulmeong.comment.dto.in.ShortsCommentRequestDto;
import com.mulmeong.comment.dto.in.ShortsCommentUpdateDto;
import com.mulmeong.comment.dto.out.ShortsCommentResponseDto;

import java.util.List;

public interface ShortsCommentService {
    void createShortsComment(ShortsCommentRequestDto requestDto);

    void updateShortsComment(ShortsCommentUpdateDto updateDto);

    void deleteShortsComment(String memberUuid, String commentUuid);

    ShortsCommentResponseDto getShortsComment(String commentUuid);

}
