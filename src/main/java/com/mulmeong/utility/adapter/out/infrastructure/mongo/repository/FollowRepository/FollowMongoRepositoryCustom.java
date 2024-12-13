package com.mulmeong.utility.adapter.out.infrastructure.mongo.repository.FollowRepository;

import com.mulmeong.utility.adapter.out.infrastructure.mongo.entity.FollowEntity;
import com.mulmeong.utility.adapter.out.infrastructure.mongo.entity.LikesEntity;
import com.mulmeong.utility.common.utils.CursorPage;
import org.springframework.stereotype.Repository;

@Repository
public interface FollowMongoRepositoryCustom {

    CursorPage<FollowEntity> getFollowers(
            String memberUuid,
            String lastId,
            Integer pageSize,
            Integer pageNo);

    CursorPage<FollowEntity> getFollowings(
            String memberUuid,
            String lastId,
            Integer pageSize,
            Integer pageNo);
}
