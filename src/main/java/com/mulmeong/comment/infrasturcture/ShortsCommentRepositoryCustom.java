package com.mulmeong.comment.infrasturcture;

import com.mulmeong.comment.common.utils.CursorPage;
import com.mulmeong.comment.dto.out.ShortsCommentResponseDto;
import org.springframework.stereotype.Repository;

@Repository
public interface ShortsCommentRepositoryCustom {

    CursorPage<ShortsCommentResponseDto> getShortsComments(
            String shortsUuid,
            String sortBy,
            Long lastId,
            Integer pageSize,
            Integer pageNo);
}
