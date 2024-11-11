package com.mulmeong.comment.infrastructure;

import com.mulmeong.comment.entity.FeedComment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

public interface FeedCommentRepository extends JpaRepository<FeedComment, Long> {
    Optional<FeedComment> findByCommentUuid(String commentUuid);

    List<FeedComment> findByMemberUuid(String memberUuid);

    List<FeedComment> findByFeedUuid(String feedUuid);

    boolean existsByCommentUuid(String commentUuid);

    boolean existsByFeedUuid(String feedUuid);

    List<FeedComment> findByStatus(boolean status);
}
