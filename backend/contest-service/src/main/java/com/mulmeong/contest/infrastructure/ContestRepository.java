package com.mulmeong.contest.infrastructure;


import com.mulmeong.contest.domain.entity.Contest;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDate;
import java.util.List;

public interface ContestRepository extends JpaRepository<Contest, Long> {
    Iterable<Contest> findByEndDate(LocalDate localDate);

    List<Contest> findByStartDateLessThanEqualAndEndDateGreaterThanEqual(LocalDate startDate, LocalDate endDate);

}
