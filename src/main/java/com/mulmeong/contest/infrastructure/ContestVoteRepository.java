package com.mulmeong.contest.infrastructure;

import com.mulmeong.contest.entity.ContestVote;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ContestVoteRepository extends JpaRepository<ContestVote, Long> {

}