package com.mulmeong.shorts.api.domain.entity;

import com.mulmeong.shorts.api.domain.model.Visibility;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import java.time.LocalDateTime;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@Entity
public class Shorts {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true, length = 36)
    private String shortsUuid;

    @Column(nullable = false, length = 36)
    private String memberUuid;

    @Column(nullable = false, length = 150)
    private String title;

    private Short playtime;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private Visibility visibility;

    @Column(nullable = false, updatable = false)
    private LocalDateTime createdAt;

    @Column(nullable = false)
    private LocalDateTime updatedAt;

    @Builder
    public Shorts(Long id, String shortsUuid, String memberUuid, String title, Short playtime,
        Visibility visibility, LocalDateTime createdAt, LocalDateTime updatedAt) {

        this.id = id;
        this.shortsUuid = shortsUuid;
        this.memberUuid = memberUuid;
        this.title = title;
        this.playtime = playtime;
        this.visibility = visibility;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
    }

}
