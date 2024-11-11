package com.mulmeong.comment.application;

import com.mulmeong.comment.common.utils.CursorPage;
import com.mulmeong.comment.dto.in.ShortsCommentRequestDto;
import com.mulmeong.comment.dto.in.ShortsCommentUpdateDto;
import com.mulmeong.comment.dto.out.ShortsCommentResponseDto;

import java.util.List;

public interface ShortsCommentService {
    void createFeedComment(ShortsCommentRequestDto requestDto);

    void updateFeedComment(ShortsCommentUpdateDto updateDto);

    void deleteShortsComment(String commentUuid);

    ShortsCommentResponseDto getShortsComment(String commentUuid);

    CursorPage<ShortsCommentResponseDto> getShortsCommentsByPage(
            String shortsUuid,
            String sortBy,
            Long lastId,
            Integer pageSize,
            Integer pageNo);
}
