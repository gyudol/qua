package com.mulmeong.contest.entity;

import jakarta.persistence.*;
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

    @Column(nullable = false)
    private Long contestId;
    @Column(nullable = false, length = 36)
    private String postUuid;
    private Long voteCount;
    private Byte ranking;


    @Builder
    public ContestWinner(
            Long contestId,
            String postUuid,
            Long voteCount,
            Byte ranking
    ) {
        this.contestId = contestId;
        this.postUuid = postUuid;
        this.voteCount = voteCount;
        this.ranking = ranking;
    }
}