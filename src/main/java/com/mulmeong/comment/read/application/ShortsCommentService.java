package com.mulmeong.comment.read.application;

import com.mulmeong.comment.read.common.utils.CursorPage;
import com.mulmeong.comment.read.dto.out.ShortsCommentResponseDto;
import com.mulmeong.event.comment.ShortsCommentCreateEvent;
import com.mulmeong.event.comment.ShortsCommentDeleteEvent;
import com.mulmeong.event.comment.ShortsCommentUpdateEvent;

public interface ShortsCommentService {

    void createShortsComment(ShortsCommentCreateEvent message);

    void updateShortsComment(ShortsCommentUpdateEvent message);

    void deleteShortsComment(ShortsCommentDeleteEvent message);

    CursorPage<ShortsCommentResponseDto> getShortsCommentsByPage(
            String shortsUuid,
            String sortBy,
            String lastId,
            Integer pageSize,
            Integer pageNo);
}
