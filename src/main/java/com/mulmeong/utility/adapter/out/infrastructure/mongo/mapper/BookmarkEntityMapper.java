package com.mulmeong.utility.adapter.out.infrastructure.mongo.mapper;

import com.mulmeong.utility.adapter.out.infrastructure.mongo.entity.FeedBookmarkEntity;
import com.mulmeong.utility.adapter.out.infrastructure.mongo.entity.ShortsBookmarkEntity;
import com.mulmeong.utility.application.port.out.dto.BookmarkResponseDto;
import org.springframework.stereotype.Component;

@Component
public class BookmarkEntityMapper {

    public FeedBookmarkEntity toFeedBookmarkEntity(BookmarkResponseDto dto) {
        return FeedBookmarkEntity.builder()
                .memberUuid(dto.getMemberUuid())
                .feedUuid(dto.getBookmarkUuid())
                .build();
    }

    public BookmarkResponseDto toDto(FeedBookmarkEntity entity) {
        return BookmarkResponseDto.builder()
                .memberUuid(entity.getMemberUuid())
                .bookmarkUuid(entity.getFeedUuid())
                .build();
    }

    public ShortsBookmarkEntity toShortsBookmarkEntity(BookmarkResponseDto dto) {
        return ShortsBookmarkEntity.builder()
                .memberUuid(dto.getMemberUuid())
                .shortsUuid(dto.getBookmarkUuid())
                .build();
    }
}
