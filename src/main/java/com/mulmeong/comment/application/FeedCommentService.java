package com.mulmeong.comment.application;

import com.mulmeong.comment.common.utils.CursorPage;
import com.mulmeong.comment.dto.in.FeedCommentRequestDto;
import com.mulmeong.comment.dto.in.FeedCommentUpdateDto;
import com.mulmeong.comment.dto.out.FeedCommentResponseDto;
import com.mulmeong.comment.entity.FeedComment;

import java.util.List;

public interface FeedCommentService {
    void createFeedComment(FeedCommentRequestDto requestDto);

    void updateFeedComment(FeedCommentUpdateDto updateDto);

    void deleteFeedComment(String commentUuid);

    List<FeedCommentResponseDto> getFeedComments(String feedUuid);

    CursorPage<FeedCommentResponseDto> getFeedCommentsByPage(
            String feedUuid,
            String sortBy,
            Long lastId,
            Integer pageSize,
            Integer pageNo);
}
