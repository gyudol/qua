package com.mulmeong.comment.read.infrsatructure;

import com.mulmeong.comment.read.common.utils.CursorPage;
import com.mulmeong.comment.read.entity.FeedComment;
import com.mulmeong.comment.read.entity.QShortsComment;
import com.mulmeong.comment.read.entity.ShortsComment;
import com.querydsl.core.BooleanBuilder;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.repository.support.SpringDataMongodbQuery;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
@RequiredArgsConstructor
@Slf4j
public class ShortsCommentRepositoryCustomImpl implements ShortsCommentRepositoryCustom {

    private static final int DEFAULT_PAGE_SIZE = 10;
    private static final int DEFAULT_PAGE_NUMBER = 0;
    private final MongoTemplate mongoTemplate;

    @Override
    public CursorPage<ShortsComment> getShortsComments(
            String shortsUuid,
            String sortBy,
            String lastId,
            Integer pageSize,
            Integer pageNo) {

        QShortsComment shortsComment = QShortsComment.shortsComment;

        BooleanBuilder builder = new BooleanBuilder();

        builder.and(shortsComment.shortsUuid.eq(shortsUuid));

        builder.and(shortsComment.status.eq(true));

        if (lastId != null) {
            if (sortBy.equals("latest")) {
                builder.and(shortsComment.id.lt(lastId));
            } else {
                builder.and(shortsComment.customCursor.lt(lastId));
            }
        }

        int currentPage = pageNo != null ? pageNo : DEFAULT_PAGE_NUMBER;
        int currentPageSize = pageSize != null ? pageSize : DEFAULT_PAGE_SIZE;
        int offset = Math.max(0, (currentPage - 1) * currentPageSize);

        SpringDataMongodbQuery<ShortsComment> query = new SpringDataMongodbQuery<>(
                mongoTemplate, ShortsComment.class);

        query.where(builder)
                .orderBy("latest".equals(sortBy)
                        ? shortsComment.id.desc()
                        : shortsComment.customCursor.desc())
                .offset(offset)
                .limit(currentPageSize + 1);

        List<ShortsComment> shortsComments = query.fetch();

        boolean hasNext = shortsComments.size() > currentPageSize;
        String nextCursor = null;
        if (hasNext) {
            shortsComments = shortsComments.subList(0, currentPageSize);  // 실제 페이지 사이즈 만큼 자르기
            if (sortBy.equals("latest")) {
                nextCursor = shortsComments.get(currentPageSize - 1).getId();
            } else {
                nextCursor = shortsComments.get(currentPageSize - 1).getCustomCursor();
            }
        }


        return new CursorPage<>(shortsComments, nextCursor, hasNext, pageSize, pageNo);
    }
}
