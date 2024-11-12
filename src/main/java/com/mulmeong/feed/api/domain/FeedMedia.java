package com.mulmeong.feed.api.domain;

import com.mulmeong.feed.api.domain.model.MediaType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@Entity
public class FeedMedia {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true, length = 36)
    private String feedUuid;

    @Column(nullable = false, length = 2083)
    private String mediaUrl;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private MediaType mediaType;

    @Column(length = 100)
    private String description;

    @Builder
    public FeedMedia(Long id, String feedUuid, String mediaUrl, MediaType mediaType,
        String description) {
        this.id = id;
        this.feedUuid = feedUuid;
        this.mediaUrl = mediaUrl;
        this.mediaType = mediaType;
        this.description = description;
    }
}
