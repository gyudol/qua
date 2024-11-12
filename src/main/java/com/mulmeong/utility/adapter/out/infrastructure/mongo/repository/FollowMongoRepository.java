package com.mulmeong.utility.adapter.out.infrastructure.mongo.repository;

import com.mulmeong.utility.adapter.out.infrastructure.mongo.entity.FollowEntity;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface FollowMongoRepository extends MongoRepository<FollowEntity, String> {

    boolean existsBySourceUuidAndTargetUuid(String sourceUuid, String targetUuid);

}
