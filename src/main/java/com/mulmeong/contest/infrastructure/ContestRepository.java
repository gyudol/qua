package com.mulmeong.contest.infrastructure;

import com.mulmeong.contest.entity.Contest;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDate;

public interface ContestRepository extends JpaRepository<Contest, Long> {
    Iterable<Contest> findByEndDate(LocalDate localDate);
}
