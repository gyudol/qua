package com.mulmeong.utility.adapter.out.infrastructure.mongo.repository;

import com.mulmeong.utility.adapter.out.infrastructure.mongo.entity.LikesEntity;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

public interface LikesMongoRepository extends MongoRepository<LikesEntity, String> {
    LikesEntity findByMemberUuidAndKindAndKindUuid(String memberUuid, String kind, String kindUuid);
    List<LikesEntity> findByMemberUuidAndKindAndStatus(String memberUuid, String kind, boolean status);
}
