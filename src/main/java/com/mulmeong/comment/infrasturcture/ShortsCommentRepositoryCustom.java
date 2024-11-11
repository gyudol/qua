package com.mulmeong.comment.infrasturcture;

import com.mulmeong.comment.common.utils.CursorPage;
import com.mulmeong.comment.dto.out.ShortsCommentResponseDto;
import com.mulmeong.comment.entity.ShortsComment;
import org.springframework.stereotype.Repository;

@Repository
public interface ShortsCommentRepositoryCustom {

    CursorPage<ShortsComment> getShortsComments(
            String shortsUuid,
            String sortBy,
            Long lastId,
            Integer pageSize,
            Integer pageNo);
}
