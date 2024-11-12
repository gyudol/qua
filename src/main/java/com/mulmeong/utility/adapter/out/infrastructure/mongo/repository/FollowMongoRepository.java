package com.mulmeong.utility.adapter.out.infrastructure.mongo.repository;

import com.mulmeong.utility.adapter.out.infrastructure.mongo.entity.FollowEntity;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.Optional;

public interface FollowMongoRepository extends MongoRepository<FollowEntity, String> {

    boolean existsBySourceUuidAndTargetUuid(String sourceUuid, String targetUuid);

    Optional<FollowEntity> findBySourceUuidAndTargetUuid(String sourceUuid, String targetUuid);
}
