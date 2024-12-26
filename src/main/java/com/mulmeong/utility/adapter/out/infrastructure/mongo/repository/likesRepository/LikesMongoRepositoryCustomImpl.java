package com.mulmeong.utility.adapter.out.infrastructure.mongo.repository.likesRepository;

import com.mulmeong.utility.adapter.out.infrastructure.mongo.entity.LikesEntity;
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
public class LikesMongoRepositoryCustomImpl implements LikesMongoRepositoryCustom {

    private static final int DEFAULT_PAGE_SIZE = 10;
    private static final int DEFAULT_PAGE_NUMBER = 0;
    private final MongoTemplate mongoTemplate;

    public CursorPage<LikesEntity> getLikesEntity(
            String memberUuid,
            String kind,
            String lastId,
            Integer pageSize,
            Integer pageNo) {

        QLikesEntity likesEntity = QLikesEntity.likesEntity;

        BooleanBuilder builder = new BooleanBuilder();
        builder.and(likesEntity.memberUuid.eq(memberUuid));
        builder.and(likesEntity.kind.eq(kind));
        builder.and(likesEntity.status.eq(true));

        if (lastId != null) {
            builder.and(likesEntity.id.goe(lastId));
        }

        int currentPage = pageNo != null ? pageNo : DEFAULT_PAGE_NUMBER;
        int currentPageSize = pageSize != null ? pageSize : DEFAULT_PAGE_SIZE;
        int offset = Math.max(0, (currentPage - 1) * currentPageSize);

        SpringDataMongodbQuery<LikesEntity> query = new SpringDataMongodbQuery<>(mongoTemplate, LikesEntity.class);

        query.where(builder)
                .orderBy(likesEntity.id.desc())
                .offset(offset)
                .limit(currentPageSize + 1);

        List<LikesEntity> likesEntities = query.fetch();

        boolean hasNext = likesEntities.size() > currentPageSize;
        String nextCursor = null;
        if (hasNext) {
            likesEntities = likesEntities.subList(0, currentPageSize);  // 실제 페이지 사이즈 만큼 자르기
            nextCursor = likesEntities.get(currentPageSize - 1).getId();
        }

        return new CursorPage<>(likesEntities, nextCursor, hasNext, currentPageSize, currentPage);
    }
}