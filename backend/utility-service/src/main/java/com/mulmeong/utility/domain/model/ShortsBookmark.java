package com.mulmeong.utility.domain.model;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Getter
@NoArgsConstructor
@ToString
public class ShortsBookmark {

    private String id;
    private String memberUuid;
    private String shortsUuid;

    @Builder
    public ShortsBookmark(
            String id,
            String memberUuid,
            String shortsUuid
    ) {
        this.id = id;
        this.memberUuid = memberUuid;
        this.shortsUuid = shortsUuid;
    }

}
