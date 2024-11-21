package com.mulmeong.comment.application;

import com.mulmeong.comment.common.utils.CursorPage;
import com.mulmeong.comment.dto.in.FeedRecommentRequestDto;
import com.mulmeong.comment.dto.in.FeedRecommentUpdateDto;
import com.mulmeong.comment.dto.out.FeedRecommentResponseDto;

import java.util.List;

public interface FeedRecommentService {
    void createFeedRecomment(FeedRecommentRequestDto requestDto);

    void updateFeedRecomment(FeedRecommentUpdateDto updateDto);

    void deleteFeedRecomment(String memberUuid, String recommentUuid);

    FeedRecommentResponseDto getFeedRecomment(String recommentUuid);

}
