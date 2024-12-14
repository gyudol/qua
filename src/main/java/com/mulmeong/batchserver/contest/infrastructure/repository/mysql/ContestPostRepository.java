package com.mulmeong.batchserver.contest.infrastructure.repository.mysql;

import com.mulmeong.batchserver.contest.domain.entity.ContestPost;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ContestPostRepository extends JpaRepository<ContestPost, Long> {
    List<ContestPost> getAllByContestId(Long contestId);
}
