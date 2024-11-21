package com.mulmeong.comment.read.application;

import com.mulmeong.comment.read.common.utils.CursorPage;
import com.mulmeong.comment.read.dto.out.FeedRecommentResponseDto;
import com.mulmeong.event.FeedRecommentCreateEvent;
import com.mulmeong.event.FeedRecommentDeleteEvent;
import com.mulmeong.event.FeedRecommentUpdateEvent;

public interface FeedRecommentService {

    void createFeedRecomment(FeedRecommentCreateEvent message);

    void updateFeedRecomment(FeedRecommentUpdateEvent message);

    void deleteFeedRecomment(FeedRecommentDeleteEvent message);

    CursorPage<FeedRecommentResponseDto> getFeedRecomments(
            String commentUuid, String lastId, Integer pageSize, Integer pageNo);
}
