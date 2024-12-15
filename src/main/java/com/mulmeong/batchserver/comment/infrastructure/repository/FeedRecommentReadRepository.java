package com.mulmeong.batchserver.comment.infrastructure.repository;


import com.mulmeong.batchserver.comment.domain.document.FeedRecomment;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.Optional;

public interface FeedRecommentReadRepository extends MongoRepository<FeedRecomment, String> {
    Optional<FeedRecomment> findByRecommentUuid(String recommentUuid);

    long countByCommentUuid(String commentUuid);
}
