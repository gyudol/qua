package com.mulmeong.comment.infrastructure;

import com.mulmeong.comment.common.utils.CursorPage;
import com.mulmeong.comment.dto.out.ShortsRecommentResponseDto;
import com.mulmeong.comment.entity.ShortsRecomment;

public interface ShortsRecommentRepositoryCustom {
    CursorPage<ShortsRecomment> getShortsReomments(
            String commentUuid,
            Long lastId,
            Integer pageSize,
            Integer pageNo);
}
