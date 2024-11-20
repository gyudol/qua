package com.mulmeong.contest.entity;

import jakarta.persistence.*;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@NoArgsConstructor
public class ContestVote {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private Long contestId;

    @Column(nullable = false, length = 36)
    private String postUuid;

    @Column(nullable = false, length = 36)
    private String memberUuid;

    @Builder
    public ContestVote(
            Long contestId,
            String postUuid,
            String memberUuid
    ) {
        this.contestId = contestId;
        this.postUuid = postUuid;
        this.memberUuid = memberUuid;
    }
}