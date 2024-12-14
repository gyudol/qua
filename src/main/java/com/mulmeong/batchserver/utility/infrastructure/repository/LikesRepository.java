package com.mulmeong.batchserver.utility.infrastructure.repository;

import com.mulmeong.batchserver.utility.domain.document.Likes;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface LikesRepository extends MongoRepository<Likes, String> {

    long countByKindAndKindUuidAndStatus(String kind, String kindUuid, boolean status);
}
