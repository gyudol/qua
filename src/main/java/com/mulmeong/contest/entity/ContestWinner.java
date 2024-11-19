package com.mulmeong.contest.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@Entity
public class ContestWinner {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Long contestId;

    private String postUuid;

    private Long voteCount;

    private Integer ranking;


    @Builder
    public ContestWinner(
            Long contestId,
            String postUuid,
            Long voteCount,
            Integer ranking
    ) {
        this.contestId = contestId;
        this.postUuid = postUuid;
        this.voteCount = voteCount;
        this.ranking = ranking;
    }
}