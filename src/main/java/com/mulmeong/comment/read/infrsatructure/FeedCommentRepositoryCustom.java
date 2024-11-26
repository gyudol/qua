package com.mulmeong.comment.read.infrsatructure;

import com.mulmeong.comment.read.common.utils.CursorPage;
import com.mulmeong.comment.read.entity.FeedComment;
import org.bson.types.ObjectId;
import org.springframework.stereotype.Repository;

@Repository
public interface FeedCommentRepositoryCustom {

    CursorPage<FeedComment> getFeedComments(
            String feedUuid,
            String sortBy,
            String lastId,
            Integer pageSize,
            Integer pageNo);
}
