package com.mulmeong.comment.application;

import com.mulmeong.comment.dto.in.ShortsCommentRequestDto;
import com.mulmeong.comment.dto.in.ShortsCommentUpdateDto;

public interface ShortsCommentService {
    void createFeedComment(ShortsCommentRequestDto requestDto);

    void updateFeedComment(ShortsCommentUpdateDto updateDto);

    void deleteShortsComment(String commentUuid);
}
