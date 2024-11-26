package com.mulmeong.comment.read.infrsatructure;


import com.mulmeong.comment.read.common.utils.CursorPage;
import com.mulmeong.comment.read.entity.ShortsRecomment;

public interface ShortsRecommentRepositoryCustom {
    CursorPage<ShortsRecomment> getShortsReomments(
            String commentUuid,
            String lastId,
            Integer pageSize,
            Integer pageNo);
}
