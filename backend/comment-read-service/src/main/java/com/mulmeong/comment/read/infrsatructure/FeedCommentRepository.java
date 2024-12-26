package com.mulmeong.comment.read.infrsatructure;

import com.mulmeong.comment.read.entity.FeedComment;
import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface FeedCommentRepository extends MongoRepository<FeedComment, String> {
    Optional<FeedComment> findByCommentUuid(String commentUuid);

    List<FeedComment> findByFeedUuid(String feedUuid);

}
