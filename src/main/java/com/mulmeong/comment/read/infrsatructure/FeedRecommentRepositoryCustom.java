package com.mulmeong.comment.read.infrsatructure;

import com.mulmeong.comment.read.common.utils.CursorPage;
import com.mulmeong.comment.read.entity.FeedComment;
import com.mulmeong.comment.read.entity.FeedRecomment;

public interface FeedRecommentRepositoryCustom {
    CursorPage<FeedRecomment> getFeedRecomments(
            String commentUuid,
            String lastId,
            Integer pageSize,
            Integer pageNo);
}
