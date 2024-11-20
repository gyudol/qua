package com.mulmeong.contest.infrastructure;

import com.mulmeong.contest.entity.ContestWinner;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ContestWinnerRepository extends JpaRepository<ContestWinner, Long> {
}
