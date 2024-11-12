package com.mulmeong.utility.adapter.out.infrastructure.mongo.entity;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Getter
@NoArgsConstructor
@Document(collection = "follow")
public class FollowEntity {

    @Id
    private String id;
    private String sourceUuid;
    private String targetUuid;

    @Builder
    public FollowEntity(
            String id,
            String sourceUuid,
            String targetUuid
    ) {
        this.id = id;
        this.sourceUuid = sourceUuid;
        this.targetUuid = targetUuid;
    }
}
