package com.mulmeong.comment.infrastructure;

import com.mulmeong.comment.common.utils.CursorPage;
import com.mulmeong.comment.dto.out.FeedCommentResponseDto;
import com.mulmeong.comment.entity.FeedComment;
import org.springframework.stereotype.Repository;

@Repository
public interface FeedCommentRepositoryCustom {

    CursorPage<FeedCommentResponseDto> getFeedComments(String feedUuid, Integer pageSize, Integer page);
}
