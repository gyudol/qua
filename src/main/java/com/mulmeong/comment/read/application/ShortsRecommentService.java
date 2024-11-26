package com.mulmeong.comment.read.application;

import com.mulmeong.comment.read.common.utils.CursorPage;
import com.mulmeong.comment.read.dto.out.ShortsRecommentResponseDto;
import com.mulmeong.event.comment.ShortsRecommentCreateEvent;
import com.mulmeong.event.comment.ShortsRecommentDeleteEvent;
import com.mulmeong.event.comment.ShortsRecommentUpdateEvent;

public interface ShortsRecommentService {

    void createShortsRecomment(ShortsRecommentCreateEvent message);

    void updateShortsRecomment(ShortsRecommentUpdateEvent message);

    void deleteShortsRecomment(ShortsRecommentDeleteEvent message);

    CursorPage<ShortsRecommentResponseDto> getShortsRecomments(
            String commentUuid, String lastId, Integer pageSize, Integer pageNo);
}
