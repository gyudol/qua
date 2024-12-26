package com.mulmeong.utility.adapter.out.infrastructure.mongo.entity;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Getter
@NoArgsConstructor
@Document(collection = "shorts_bookmark")
public class ShortsBookmarkEntity {

    @Id
    private String id;
    private String memberUuid;
    private String shortsUuid;

    @Builder
    public ShortsBookmarkEntity(
            String id,
            String memberUuid,
            String shortsUuid
    ) {
        this.id = id;
        this.memberUuid = memberUuid;
        this.shortsUuid = shortsUuid;
    }

}
