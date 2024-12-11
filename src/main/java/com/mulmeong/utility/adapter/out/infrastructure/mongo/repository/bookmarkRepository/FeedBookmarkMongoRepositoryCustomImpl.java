package com.mulmeong.utility.adapter.out.infrastructure.mongo.repository.bookmarkRepository;

import com.mulmeong.utility.adapter.out.infrastructure.mongo.entity.FeedBookmarkEntity;
import com.mulmeong.utility.adapter.out.infrastructure.mongo.entity.LikesEntity;
import com.mulmeong.utility.adapter.out.infrastructure.mongo.entity.QFeedBookmarkEntity;
import com.mulmeong.utility.adapter.out.infrastructure.mongo.entity.QLikesEntity;
import com.mulmeong.utility.common.utils.CursorPage;
import com.querydsl.core.BooleanBuilder;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.repository.support.SpringDataMongodbQuery;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@RequiredArgsConstructor
@Slf4j
public class FeedBookmarkMongoRepositoryCustomImpl implements FeedBookmarkMongoRepositoryCustom {

    private static final int DEFAULT_PAGE_SIZE = 10;
    private static final int DEFAULT_PAGE_NUMBER = 0;
    private final MongoTemplate mongoTemplate;

    @Override
    public CursorPage<FeedBookmarkEntity> getFeedBookmarks(
            String memberUuid,
            String lastId,
            Integer pageSize,
            Integer pageNo) {

        QFeedBookmarkEntity feedBookmarkEntity = QFeedBookmarkEntity.feedBookmarkEntity;

        BooleanBuilder builder = new BooleanBuilder();
        builder.and(feedBookmarkEntity.memberUuid.eq(memberUuid));

        if (lastId != null) {
            builder.and(feedBookmarkEntity.id.lt(lastId));
        }

        int currentPage = pageNo != null ? pageNo : DEFAULT_PAGE_NUMBER;
        int currentPageSize = pageSize != null ? pageSize : DEFAULT_PAGE_SIZE;
        int offset = Math.max(0, (currentPage - 1) * currentPageSize);

        SpringDataMongodbQuery<FeedBookmarkEntity> query =
                new SpringDataMongodbQuery<>(mongoTemplate, FeedBookmarkEntity.class);

        query.where(builder)
                .orderBy(feedBookmarkEntity.id.desc())
                .offset(offset)
                .limit(currentPageSize + 1);

        List<FeedBookmarkEntity> feedBookmarkEntities = query.fetch();

        boolean hasNext = feedBookmarkEntities.size() > currentPageSize;
        String nextCursor = null;
        if (hasNext) {
            feedBookmarkEntities = feedBookmarkEntities.subList(0, currentPageSize);  // 실제 페이지 사이즈 만큼 자르기
            nextCursor = feedBookmarkEntities.get(currentPageSize - 1).getId();
        }

        return new CursorPage<>(feedBookmarkEntities, nextCursor, hasNext, currentPageSize, currentPage);
    }
}
