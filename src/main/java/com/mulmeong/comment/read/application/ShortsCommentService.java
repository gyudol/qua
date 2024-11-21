package com.mulmeong.comment.read.application;

import com.mulmeong.comment.read.common.utils.CursorPage;
import com.mulmeong.comment.read.dto.out.FeedCommentResponseDto;
import com.mulmeong.comment.read.dto.out.ShortsCommentResponseDto;
import com.mulmeong.event.ShortsCommentCreateEvent;
import com.mulmeong.event.ShortsCommentDeleteEvent;
import com.mulmeong.event.ShortsCommentUpdateEvent;
import org.bouncycastle.util.Shorts;

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
