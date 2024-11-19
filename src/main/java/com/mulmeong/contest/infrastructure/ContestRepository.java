package com.mulmeong.contest.infrastructure;

import com.mulmeong.contest.entity.Contest;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ContestRepository extends JpaRepository<Contest, Long> {
}
