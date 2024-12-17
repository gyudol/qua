package com.mulmeong.batchserver.comment.infrastructure.repository;

import com.mulmeong.batchserver.comment.domain.document.ShortsComment;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.Optional;

public interface ShortsCommentReadRepository extends MongoRepository<ShortsComment, String> {

    Optional<ShortsComment> findByCommentUuid(String commentUuid);

    long countByShortsUuidAndIsDeletedFalse(String shortsUuid);
}
