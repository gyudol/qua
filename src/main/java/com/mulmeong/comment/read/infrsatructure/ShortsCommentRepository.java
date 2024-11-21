package com.mulmeong.comment.read.infrsatructure;

import com.mulmeong.comment.read.entity.FeedComment;
import com.mulmeong.comment.read.entity.ShortsComment;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ShortsCommentRepository extends MongoRepository<ShortsComment, String> {
    Optional<ShortsComment> findByCommentUuid(String commentUuid);

    Optional<ShortsComment> findByShortsUuid(String shortsUuid);

    boolean existsByCommentUuid(String commentUuid);
}
