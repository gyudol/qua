package com.mulmeong.batchserver.utility.infrastructure.repository;

import com.mulmeong.batchserver.utility.domain.document.Dislike;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface DislikesRepository extends MongoRepository<Dislike, String> {

    long countByKindAndKindUuidAndStatus(String kind, String kindUuid, boolean status);
}
