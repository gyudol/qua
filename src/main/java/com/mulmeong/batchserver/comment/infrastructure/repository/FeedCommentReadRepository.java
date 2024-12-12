package com.mulmeong.batchserver.comment.infrastructure.repository;


import com.mulmeong.batchserver.comment.domain.document.FeedComment;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface FeedCommentReadRepository extends MongoRepository<FeedComment, String> {

    Optional<FeedComment> findByCommentUuid(String commentUuid);


    long countByFeedUuid(String feedUuid);
}
