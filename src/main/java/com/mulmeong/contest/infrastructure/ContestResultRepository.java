package com.mulmeong.contest.infrastructure;


import com.mulmeong.contest.domain.entity.ContestResult;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ContestResultRepository extends JpaRepository<ContestResult, Long> {
}
