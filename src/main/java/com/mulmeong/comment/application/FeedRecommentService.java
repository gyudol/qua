package com.mulmeong.comment.application;

import com.mulmeong.comment.dto.in.FeedRecommentRequestDto;
import com.mulmeong.comment.dto.in.FeedRecommentUpdateDto;

public interface FeedRecommentService {
    void createFeedComment(FeedRecommentRequestDto requestDto);

    void updateFeedComment(FeedRecommentUpdateDto updateDto);

    void deleteFeedComment(String recommentUuid);
}
