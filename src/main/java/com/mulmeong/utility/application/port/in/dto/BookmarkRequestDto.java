package com.mulmeong.utility.application.port.in.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class BookmarkRequestDto {

    private String memberUuid;
    private String feedUuid;

    @Builder
    public BookmarkRequestDto(
            String memberUuid,
            String feedUuid
    ) {
        this.memberUuid = memberUuid;
        this.feedUuid = feedUuid;
    }
}
