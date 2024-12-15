package com.mulmeong.batchserver.contest.domain.entity;

import jakarta.persistence.*;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.LocalDateTime;

@Getter
@NoArgsConstructor
@Entity
@EntityListeners(AuditingEntityListener.class)
public class ContestPost {

    @Id
    private Long id;
    private String postUuid;
    private Long contestId;
    private String memberUuid;
    private LocalDateTime createdAt;

    @Builder
    public ContestPost(
            Long id,
            String postUuid,
            Long contestId,
            String memberUuid,
            LocalDateTime createdAt
    ) {
        this.id = id;
        this.postUuid = postUuid;
        this.contestId = contestId;
        this.memberUuid = memberUuid;
        this.createdAt = createdAt;
    }

}
