package com.mulmeong.batchserver.comment.infrastructure.repository;

import com.mulmeong.batchserver.comment.domain.document.ShortsRecomment;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ShortsRecommentReadRepository extends MongoRepository<ShortsRecomment, String> {
    Optional<ShortsRecomment> findByRecommentUuid(String recommentUuid);
}
