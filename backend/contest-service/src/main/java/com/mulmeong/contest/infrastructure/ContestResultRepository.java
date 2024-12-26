package com.mulmeong.contest.infrastructure;


import com.mulmeong.contest.domain.entity.ContestResult;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Collection;

public interface ContestResultRepository extends JpaRepository<ContestResult, Long> {
    Collection<ContestResult> findByContestIdOrderByRankingAsc(Long id);
}
