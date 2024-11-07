package com.mulmeong.comment.infrastructure;

import com.mulmeong.comment.common.utils.CursorPage;
import com.mulmeong.comment.entity.FeedRecomment;

public interface FeedRecommentRepositoryCustom {
    CursorPage<FeedRecomment> getFeedComments(
            String commentUuid,
            Long lastId,
            Integer pageSize,
            Integer pageNo);
}
