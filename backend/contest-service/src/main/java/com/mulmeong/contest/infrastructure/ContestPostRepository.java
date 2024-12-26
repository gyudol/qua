package com.mulmeong.contest.infrastructure;

import com.mulmeong.contest.domain.entity.ContestPost;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ContestPostRepository extends JpaRepository<ContestPost, Long> {

    boolean existsByContestIdAndMemberUuid(Long contestId, String memberUuid);

    List<ContestPost> getAllByContestId(Long contestId);
}
