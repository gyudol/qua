package com.mulmeong.utility.adapter.out.infrastructure.mongo.repository.likesRepository;

import com.mulmeong.utility.adapter.out.infrastructure.mongo.entity.LikesEntity;
import com.mulmeong.utility.common.utils.CursorPage;
import org.springframework.stereotype.Repository;

@Repository
public interface LikesMongoRepositoryCustom {

    CursorPage<LikesEntity> getLikesEntity(
            String memberUuid,
            String kind,
            String lastId,
            Integer pageSize,
            Integer pageNo);
}
