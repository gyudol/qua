package com.mulmeong.contest.entity;

import jakarta.persistence.*;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@Entity
public class ContestResult {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(nullable = false)
    private String memberUuid;
    @Column(nullable = false)
    private Long contestId;
    @Column(nullable = false, length = 36)
    private String postUuid;
    private Long badgeId;
    private Long voteCount;
    private Byte ranking;


    @Builder
    public ContestResult(
            Long contestId,
            String memberUuid,
            String postUuid,
            Long badgeId,
            Long voteCount,
            Byte ranking
    ) {
        this.contestId = contestId;
        this.memberUuid = memberUuid;
        this.postUuid = postUuid;
        this.badgeId = badgeId;
        this.voteCount = voteCount;
        this.ranking = ranking;
    }
}