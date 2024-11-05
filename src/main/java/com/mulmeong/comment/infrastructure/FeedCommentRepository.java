package com.mulmeong.comment.infrastructure;

import com.mulmeong.comment.entity.FeedComment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

public interface FeedCommentRepository extends JpaRepository<FeedComment, Long> {
    Optional<FeedComment> findByCommentUuid(String commentUuid);

    Optional<FeedComment> findByMemberUuid(String memberUuid);

    Optional<FeedComment> findByFeedUuid(String feedUuid);

    Boolean existsByCommentUuid(String commentUuid);

    Boolean existsByFeedUuid(String feedUuid);

    List<FeedComment> findByStatus(Boolean status);

}
