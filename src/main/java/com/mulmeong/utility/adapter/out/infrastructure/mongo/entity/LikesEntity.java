package com.mulmeong.utility.adapter.out.infrastructure.mongo.entity;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Getter
@NoArgsConstructor
@Document(collection = "likes")
public class LikesEntity {

    @Id
    private String id;
    private String memberUuid;
    private String kind;
    private String kindUuid;
    private boolean status;

    @Builder
    public LikesEntity(
            String id,
            String memberUuid,
            String kind,
            String kindUuid,
            boolean status) {
        this.id = id;
        this.memberUuid = memberUuid;
        this.kind = kind;
        this.kindUuid = kindUuid;
        this.status = status;
    }
}
