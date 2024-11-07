package com.mulmeong.comment.application;

import com.mulmeong.comment.common.utils.CursorPage;
import com.mulmeong.comment.dto.in.FeedRecommentRequestDto;
import com.mulmeong.comment.dto.in.FeedRecommentUpdateDto;
import com.mulmeong.comment.dto.out.FeedRecommentResponseDto;

import java.util.List;

public interface FeedRecommentService {
    void createFeedComment(FeedRecommentRequestDto requestDto);

    void updateFeedComment(FeedRecommentUpdateDto updateDto);

    void deleteFeedComment(String recommentUuid);

    CursorPage<FeedRecommentResponseDto> getFeedRecomments(
            String commentUuid, Long lastId, Integer pageSize, Integer pageNo);
}
