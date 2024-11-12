package com.mulmeong.feed.api.domain;

import jakarta.persistence.Column;
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
public class FeedHashtag {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true, length = 36)
    private String feedUuid;

    @Column(nullable = false, length = 50)
    private String name;

    @Builder
    public FeedHashtag(Long id, String feedUuid, String name) {
        this.id = id;
        this.feedUuid = feedUuid;
        this.name = name;
    }

}
