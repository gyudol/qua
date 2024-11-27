package com.mulmeong.comment.read.infrsatructure;

import com.mulmeong.comment.read.common.utils.CursorPage;
import com.mulmeong.comment.read.entity.FeedComment;
import com.mulmeong.comment.read.entity.QFeedComment;
import com.querydsl.core.BooleanBuilder;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.data.mongodb.repository.support.SpringDataMongodbQuery;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
@RequiredArgsConstructor
@Slf4j
public class FeedCommentRepositoryCustomImpl implements FeedCommentRepositoryCustom {

    private static final int DEFAULT_PAGE_SIZE = 10;
    private static final int DEFAULT_PAGE_NUMBER = 0;
    private final MongoTemplate mongoTemplate;

    @Override
    public CursorPage<FeedComment> getFeedComments(
            String feedUuid,
            String sortBy,
            String lastId,
            Integer pageSize,
            Integer pageNo) {

        QFeedComment feedComment = QFeedComment.feedComment;

        BooleanBuilder builder = new BooleanBuilder();

        builder.and(feedComment.feedUuid.eq(feedUuid));
        if (sortBy.equals("latest")) {
            if (lastId != null) {
                builder.and(feedComment.id.lt(lastId));
            }
            builder.and(feedComment.isDeleted.eq(false)
                    .or(feedComment.isDeleted.eq(true)
                            .and(feedComment.recommentCount.goe(1))));

        } else {
            if (lastId != null) {
                builder.and(feedComment.customCursor.lt(lastId));
            }
            builder.and(feedComment.isDeleted.eq(false));
        }

        int currentPage = pageNo != null ? pageNo : DEFAULT_PAGE_NUMBER;
        int currentPageSize = pageSize != null ? pageSize : DEFAULT_PAGE_SIZE;
        int offset = Math.max(0, (currentPage - 1) * currentPageSize);

        SpringDataMongodbQuery<FeedComment> query = new SpringDataMongodbQuery<>(
                mongoTemplate, FeedComment.class);

        query.where(builder)
                .orderBy("latest".equals(sortBy)
                        ? feedComment.id.desc()
                        : feedComment.customCursor.desc())
                .offset(offset)
                .limit(currentPageSize + 1);

        List<FeedComment> feedComments = query.fetch();

        boolean hasNext = feedComments.size() > currentPageSize;
        String nextCursor = null;
        if (hasNext) {
            feedComments = feedComments.subList(0, currentPageSize);  // 실제 페이지 사이즈 만큼 자르기
            if (sortBy.equals("latest")) {
                nextCursor = feedComments.get(currentPageSize - 1).getId();
            } else {
                nextCursor = feedComments.get(currentPageSize - 1).getCustomCursor();
            }
        }


        return new CursorPage<>(feedComments, nextCursor, hasNext, pageSize, pageNo);
    }

}
