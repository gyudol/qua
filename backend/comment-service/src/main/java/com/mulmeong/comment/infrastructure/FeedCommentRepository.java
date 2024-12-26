package com.mulmeong.comment.infrastructure;

import com.mulmeong.comment.entity.FeedComment;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface FeedCommentRepository extends JpaRepository<FeedComment, Long> {
    Optional<FeedComment> findByCommentUuid(String commentUuid);

    boolean existsByCommentUuid(String commentUuid);

}
