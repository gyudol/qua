package com.mulmeong.comment.read.infrsatructure;


import com.mulmeong.comment.read.common.utils.CursorPage;
import com.mulmeong.comment.read.entity.ShortsComment;
import org.springframework.stereotype.Repository;

@Repository
public interface ShortsCommentRepositoryCustom {

    CursorPage<ShortsComment> getShortsComments(
            String shortsUuid,
            String sortBy,
            String lastId,
            Integer pageSize,
            Integer pageNo);
}
