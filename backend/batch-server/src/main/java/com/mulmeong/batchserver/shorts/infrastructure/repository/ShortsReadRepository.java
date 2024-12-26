package com.mulmeong.batchserver.shorts.infrastructure.repository;

import com.mulmeong.batchserver.shorts.domain.document.ShortsRead;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.Optional;

public interface ShortsReadRepository extends MongoRepository<ShortsRead, String> {

    Optional<ShortsRead> findByShortsUuid(String shortsUuid);

}
