package com.mulmeong.comment.infrastructure;

import com.mulmeong.comment.entity.ShortsRecomment;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface ShortsRecommentRepository extends JpaRepository<ShortsRecomment, Long> {
    List<ShortsRecomment> findByMemberUuid(String memberUuid);

    List<ShortsRecomment> findByCommentUuid(String memberUuid);

    Optional<ShortsRecomment> findByRecommentUuid(String memberUuid);
}
