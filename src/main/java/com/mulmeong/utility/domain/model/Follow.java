package com.mulmeong.utility.domain.model;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Getter
@NoArgsConstructor
@ToString
public class Follow {

    private String id;
    private String sourceUuid;
    private String targetUuid;

    @Builder
    public Follow(
            String id,
            String sourceUuid,
            String targetUuid
    ) {
        this.id = id;
        this.sourceUuid = sourceUuid;
        this.targetUuid = targetUuid;
    }

}
