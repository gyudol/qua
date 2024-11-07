package com.mulmeong.utility.adapter.in.web.vo;

import com.mulmeong.utility.application.port.in.dto.BookmarkRequestDto;
import lombok.Getter;
import lombok.ToString;

@ToString
@Getter
public class BookmarkRequestVo {

    private String feedUuid;

    public BookmarkRequestDto toDto(String memberUuid) {
        return BookmarkRequestDto.builder()
                .memberUuid(memberUuid)
                .feedUuid(feedUuid)
                .build();
    }
}
