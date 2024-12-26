package com.mulmeong.utility.adapter.out.infrastructure.mongo.repository.bookmarkRepository;


import com.mulmeong.utility.adapter.out.infrastructure.mongo.entity.QShortsBookmarkEntity;
import com.mulmeong.utility.adapter.out.infrastructure.mongo.entity.ShortsBookmarkEntity;
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
public class ShortsBookmarkMongoRepositoryCustomImpl implements ShortsBookmarkMongoRepositoryCustom {

    private static final int DEFAULT_PAGE_SIZE = 10;
    private static final int DEFAULT_PAGE_NUMBER = 0;
    private final MongoTemplate mongoTemplate;

    @Override
    public CursorPage<ShortsBookmarkEntity> getShortsBookmarks(
            String memberUuid,
            String lastId,
            Integer pageSize,
            Integer pageNo) {

        QShortsBookmarkEntity shortsBookmarkEntity = QShortsBookmarkEntity.shortsBookmarkEntity;

        BooleanBuilder builder = new BooleanBuilder();
        builder.and(shortsBookmarkEntity.memberUuid.eq(memberUuid));

        if (lastId != null) {
            builder.and(shortsBookmarkEntity.id.goe(lastId));
        }

        int currentPage = pageNo != null ? pageNo : DEFAULT_PAGE_NUMBER;
        int currentPageSize = pageSize != null ? pageSize : DEFAULT_PAGE_SIZE;
        int offset = Math.max(0, (currentPage - 1) * currentPageSize);

        SpringDataMongodbQuery<ShortsBookmarkEntity> query =
                new SpringDataMongodbQuery<>(mongoTemplate, ShortsBookmarkEntity.class);

        query.where(builder)
                .orderBy(shortsBookmarkEntity.id.desc())
                .offset(offset)
                .limit(currentPageSize + 1);

        List<ShortsBookmarkEntity> shortsBookmarkEntities = query.fetch();

        boolean hasNext = shortsBookmarkEntities.size() > currentPageSize;
        String nextCursor = null;
        if (hasNext) {
            shortsBookmarkEntities = shortsBookmarkEntities.subList(0, currentPageSize);  // 실제 페이지 사이즈 만큼 자르기
            nextCursor = shortsBookmarkEntities.get(currentPageSize - 1).getId();
        }

        return new CursorPage<>(shortsBookmarkEntities, nextCursor, hasNext, currentPageSize, currentPage);
    }
}
