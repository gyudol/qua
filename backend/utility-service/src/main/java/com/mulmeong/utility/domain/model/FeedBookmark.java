package com.mulmeong.utility.domain.model;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Getter
@NoArgsConstructor
@ToString
public class FeedBookmark {

    private String id;
    private String memberUuid;
    private String feedUuid;

    @Builder
    public FeedBookmark(
            String id,
            String memberUuid,
            String feedUuid
    ) {
        this.id = id;
        this.memberUuid = memberUuid;
        this.feedUuid = feedUuid;
    }

}
