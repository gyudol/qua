package com.mulmeong.contest.domain.entity;

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
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(nullable = false, unique = true, length = 36)
    private String postUuid;
    @Column(nullable = false)
    private Long contestId;
    @Column(nullable = false, length = 36)
    private String memberUuid;
    @Column(updatable = false)
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
