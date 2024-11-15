package com.mulmeong.contest.entity;

import jakarta.persistence.*;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.LocalDateTime;

@Getter
@NoArgsConstructor
@Entity
@EntityListeners(AuditingEntityListener.class)
public class Post {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(nullable = false)
    private Long contestId;
    @Column(nullable = false)
    private String memberUuid;
    @Column(nullable = false, length = 2083)
    private String mediaUrl;
    @Enumerated(EnumType.STRING)
    private MediaType mediaType;
    @CreatedDate
    @Column(updatable = false)
    private LocalDateTime createdAt;

    @Builder
    public Post(
            Long id,
            Long contestId,
            String memberUuid,
            String mediaUrl,
            MediaType mediaType
    ) {
        this.id = id;
        this.contestId = contestId;
        this.memberUuid = memberUuid;
        this.mediaUrl = mediaUrl;
        this.mediaType = mediaType;
    }

    @PrePersist
    protected void onCreate() {
        this.createdAt = LocalDateTime.now();
    }
}
