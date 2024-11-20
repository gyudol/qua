package com.mulmeong.contest.infrastructure;

import com.mulmeong.contest.entity.ContestPost;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ContestPostRepository extends JpaRepository<ContestPost, Long> {

    boolean existsByContestIdAndMemberUuid(Long contestId, String memberUuid);

}
