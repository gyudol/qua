package com.mulmeong.utility.adapter.out.infrastructure.mongo.repository;

import com.mulmeong.utility.adapter.out.infrastructure.mongo.entity.FollowEntity;
import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;
import java.util.Optional;

public interface FollowMongoRepository extends MongoRepository<FollowEntity, String> {

    boolean existsBySourceUuidAndTargetUuid(String sourceUuid, String targetUuid);

    Optional<FollowEntity> findBySourceUuidAndTargetUuid(String sourceUuid, String targetUuid);

    List<FollowEntity> findByTargetUuidAndIdLessThanOrderByIdDesc(String memberUuid, String lastId, Pageable pageable);

    List<FollowEntity> findByTargetUuidOrderByIdDesc(String memberUuid, Pageable pageable);
}
