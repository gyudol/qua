package com.mulmeong.utility.adapter.out.infrastructure.mongo.repository.FollowRepository;

import com.mulmeong.utility.adapter.out.infrastructure.mongo.entity.FollowEntity;
import com.mulmeong.utility.adapter.out.infrastructure.mongo.entity.QFollowEntity;
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
public class FollowMongoRepositoryCustomImpl implements FollowMongoRepositoryCustom {

    private static final int DEFAULT_PAGE_SIZE = 10;
    private static final int DEFAULT_PAGE_NUMBER = 0;
    private final MongoTemplate mongoTemplate;

    @Override
    public CursorPage<FollowEntity> getFollowers(
            String memberUuid,
            String lastId,
            Integer pageSize,
            Integer pageNo
    ) {
        QFollowEntity followEntity = QFollowEntity.followEntity;

        BooleanBuilder builder = new BooleanBuilder();
        builder.and(followEntity.targetUuid.eq(memberUuid));

        if (lastId != null) {
            builder.and(followEntity.id.lt(lastId));
        }

        int currentPage = pageNo != null ? pageNo : DEFAULT_PAGE_NUMBER;
        int currentPageSize = pageSize != null ? pageSize : DEFAULT_PAGE_SIZE;
        int offset = Math.max(0, (currentPage - 1) * currentPageSize);

        SpringDataMongodbQuery<FollowEntity> query = new SpringDataMongodbQuery<>(mongoTemplate, FollowEntity.class);

        query.where(builder)
                .orderBy(followEntity.id.desc())
                .offset(offset)
                .limit(currentPageSize + 1);

        List<FollowEntity> entities = query.fetch();

        boolean hasNext = entities.size() > currentPageSize;
        String nextCursor = null;
        if (hasNext) {
            entities = entities.subList(0, currentPageSize);
            nextCursor = entities.get(currentPageSize - 1).getId();
        }

        return CursorPage.<FollowEntity>builder()
                .content(entities)
                .nextCursor(nextCursor)
                .hasNext(hasNext)
                .pageSize(currentPageSize)
                .pageNo(currentPage)
                .build();
    }

    @Override
    public CursorPage<FollowEntity> getFollowings(
            String memberUuid,
            String lastId,
            Integer pageSize,
            Integer pageNo
    ) {
        QFollowEntity followEntity = QFollowEntity.followEntity;

        BooleanBuilder builder = new BooleanBuilder();
        builder.and(followEntity.sourceUuid.eq(memberUuid));

        if (lastId != null) {
            builder.and(followEntity.id.lt(lastId));
        }

        int currentPage = pageNo != null ? pageNo : DEFAULT_PAGE_NUMBER;
        int currentPageSize = pageSize != null ? pageSize : DEFAULT_PAGE_SIZE;
        int offset = Math.max(0, (currentPage - 1) * currentPageSize);

        SpringDataMongodbQuery<FollowEntity> query = new SpringDataMongodbQuery<>(mongoTemplate, FollowEntity.class);

        query.where(builder)
                .orderBy(followEntity.id.desc())
                .offset(offset)
                .limit(currentPageSize + 1);

        List<FollowEntity> entities = query.fetch();

        boolean hasNext = entities.size() > currentPageSize;
        String nextCursor = null;
        if (hasNext) {
            entities = entities.subList(0, currentPageSize);
            nextCursor = entities.get(currentPageSize - 1).getId();
        }

        return CursorPage.<FollowEntity>builder()
                .content(entities)
                .nextCursor(nextCursor)
                .hasNext(hasNext)
                .pageSize(currentPageSize)
                .pageNo(currentPage)
                .build();
    }
}
