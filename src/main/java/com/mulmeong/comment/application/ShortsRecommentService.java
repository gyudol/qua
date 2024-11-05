package com.mulmeong.comment.application;

import com.mulmeong.comment.dto.in.ShortsRecommentRequestDto;
import com.mulmeong.comment.dto.in.ShortsRecommentUpdateDto;

public interface ShortsRecommentService {
    void createFeedComment(ShortsRecommentRequestDto requestDto);

    void updateFeedComment(ShortsRecommentUpdateDto updateDto);

    void deleteFeedComment(String recommentUuid);
}
