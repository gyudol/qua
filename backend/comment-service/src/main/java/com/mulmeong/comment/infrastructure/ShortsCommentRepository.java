package com.mulmeong.comment.infrastructure;

import com.mulmeong.comment.entity.ShortsComment;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ShortsCommentRepository extends JpaRepository<ShortsComment, Long> {
    Optional<ShortsComment> findByCommentUuid(String commentUuid);

    boolean existsByCommentUuid(String commentUuid);

}
