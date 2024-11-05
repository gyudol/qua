package com.mulmeong.comment.application;

import com.mulmeong.comment.dto.in.FeedCommentRequestDto;
import com.mulmeong.comment.dto.in.FeedCommentUpdateDto;

public interface FeedCommentService {
    void createFeedComment(FeedCommentRequestDto requestDto);

    void updateFeedComment(FeedCommentUpdateDto updateDto);

    void deleteFeedComment(String commentUuid);

}
