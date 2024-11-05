package com.mulmeong.utility.adapter.out.infrastructure.mongo.repository;

import com.mulmeong.utility.adapter.out.infrastructure.mongo.entity.DislikeEntity;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface DislikeMongoRepository extends MongoRepository<DislikeEntity, String> {
    DislikeEntity findByMemberUuidAndKindAndKindUuid(String memberUuid, String kind, String kindUuid);
}
