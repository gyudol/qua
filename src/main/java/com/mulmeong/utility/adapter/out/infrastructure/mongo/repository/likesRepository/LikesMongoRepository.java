package com.mulmeong.utility.adapter.out.infrastructure.mongo.repository.likesRepository;

import com.mulmeong.utility.adapter.out.infrastructure.mongo.entity.LikesEntity;
import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

public interface LikesMongoRepository extends MongoRepository<LikesEntity, String> {
    LikesEntity findByMemberUuidAndKindAndKindUuid(String memberUuid, String kind, String kindUuid);

    List<LikesEntity> findByMemberUuidAndKindAndStatusAndIdLessThanOrderByIdDesc(String memberUuid, String kind, boolean status, String lastId, Pageable pageable);

    List<LikesEntity> findByMemberUuidAndKindAndStatusOrderByIdDesc(String memberUuid, String kind, boolean status, Pageable pageable);
}
