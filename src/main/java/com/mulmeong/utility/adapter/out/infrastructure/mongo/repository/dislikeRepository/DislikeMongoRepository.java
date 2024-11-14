package com.mulmeong.utility.adapter.out.infrastructure.mongo.repository.dislikeRepository;

import com.mulmeong.utility.adapter.out.infrastructure.mongo.entity.DislikeEntity;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;
import java.util.Optional;

public interface DislikeMongoRepository extends MongoRepository<DislikeEntity, String> {
    Optional<DislikeEntity> findByMemberUuidAndKindAndKindUuid(String memberUuid, String kind, String kindUuid);

    List<DislikeEntity> findByMemberUuidAndKindAndStatus(String memberUuid, String kind, boolean b);

}
