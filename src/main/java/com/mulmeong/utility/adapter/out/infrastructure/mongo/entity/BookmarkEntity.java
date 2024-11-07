package com.mulmeong.utility.adapter.out.infrastructure.mongo.entity;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Getter
@NoArgsConstructor
@Document(collection = "bookmark")
public class BookmarkEntity {

    @Id
    private String id;
    private String memberUuid;
    private String bookmarkUuid;

    @Builder
    public BookmarkEntity(
            String id,
            String memberUuid,
            String bookmarkUuid
    ) {
        this.id = id;
        this.memberUuid = memberUuid;
        this.bookmarkUuid = bookmarkUuid;
    }
}
