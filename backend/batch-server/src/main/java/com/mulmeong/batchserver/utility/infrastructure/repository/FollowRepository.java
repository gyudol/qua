package com.mulmeong.batchserver.utility.infrastructure.repository;

import com.mulmeong.batchserver.utility.domain.document.Follow;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

public interface FollowRepository extends MongoRepository<Follow, String> {
    List<Follow> findAllByTargetUuid(String targetUuid);

    Integer countByTargetUuid(String targetUuid);

    Integer countBySourceUuid(String sourceUuid);

}
