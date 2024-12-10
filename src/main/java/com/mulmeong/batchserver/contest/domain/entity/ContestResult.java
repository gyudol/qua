package com.mulmeong.batchserver.contest.domain.entity;

import jakarta.persistence.*;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@Entity
public class ContestResult {

    @Id
    private Long id;
    private String memberUuid;
    private Long contestId;
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