package com.mulmeong.batchserver.comment.infrastructure.repository;


import com.mulmeong.batchserver.comment.domain.document.FeedComment;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.Optional;

public interface FeedCommentReadRepository extends MongoRepository<FeedComment, String> {

    Optional<FeedComment> findByCommentUuid(String commentUuid);


    long countByFeedUuidAndIsDeletedFalse(String feedUuid);
}
