package com.mulmeong.utility.application.port.in.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class BookmarkRequestDto {

    private String memberUuid;
    private String bookmarkUuid;

    @Builder
    public BookmarkRequestDto(
            String memberUuid,
            String bookmarkUuid
    ) {
        this.memberUuid = memberUuid;
        this.bookmarkUuid = bookmarkUuid;
    }
}
