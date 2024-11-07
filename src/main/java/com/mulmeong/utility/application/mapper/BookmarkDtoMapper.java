package com.mulmeong.utility.application.mapper;

import com.mulmeong.utility.application.port.out.dto.BookmarkResponseDto;
import com.mulmeong.utility.domain.model.Bookmark;
import org.springframework.stereotype.Component;

@Component
public class BookmarkDtoMapper {

    public BookmarkResponseDto toDto(Bookmark bookmark) {
        return BookmarkResponseDto.builder()
                .memberUuid(bookmark.getMemberUuid())
                .bookmarkUuid(bookmark.getBookmarkUuid())
                .build();
    }
}
