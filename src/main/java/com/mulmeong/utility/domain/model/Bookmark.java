package com.mulmeong.utility.domain.model;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Getter
@NoArgsConstructor
@ToString
public class Bookmark {

    private String memberUuid;
    private String bookmarkUuid;

    @Builder
    public Bookmark(
            String memberUuid,
            String bookmarkUuid
    ) {
        this.memberUuid = memberUuid;
        this.bookmarkUuid = bookmarkUuid;
    }
}
