package com.mulmeong.batchserver.contest.infrastructure.repository;

import com.mulmeong.batchserver.contest.domain.entity.Contest;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ContestRepository extends JpaRepository<Contest, Long> {

    Iterable<Contest> findByStatusTrue();
}
