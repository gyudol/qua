package com.mulmeong.utility.adapter.out.infrastructure.mongo.entity;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Getter
@NoArgsConstructor
@Document(collection = "feed_bookmark")
public class FeedBookmarkEntity {

    @Id
    private String id;
    private String memberUuid;
    private String feedUuid;

    @Builder
    public FeedBookmarkEntity(
            String id,
            String memberUuid,
            String feedUuid
    ) {
        this.id = id;
        this.memberUuid = memberUuid;
        this.feedUuid = feedUuid;
    }
}
