package com.mulmeong.utility.adapter.out.infrastructure.mongo.mapper;

import com.mulmeong.utility.adapter.out.infrastructure.mongo.entity.FeedBookmarkEntity;
import com.mulmeong.utility.adapter.out.infrastructure.mongo.entity.ShortsBookmarkEntity;
import com.mulmeong.utility.application.port.out.dto.FeedBookmarkResponseDto;
import com.mulmeong.utility.application.port.out.dto.ShortsBookmarkResponseDto;
import org.springframework.stereotype.Component;

@Component
public class BookmarkEntityMapper {

    public FeedBookmarkEntity toFeedBookmarkEntity(FeedBookmarkResponseDto dto) {
        return FeedBookmarkEntity.builder()
                .memberUuid(dto.getMemberUuid())
                .feedUuid(dto.getFeedUuid())
                .build();
    }

    public FeedBookmarkResponseDto toDto(FeedBookmarkEntity entity) {
        return FeedBookmarkResponseDto.builder()
                .memberUuid(entity.getMemberUuid())
                .feedUuid(entity.getFeedUuid())
                .build();
    }

    public ShortsBookmarkResponseDto toDto(ShortsBookmarkEntity entity) {
        return ShortsBookmarkResponseDto.builder()
                .memberUuid(entity.getMemberUuid())
                .shortsUuid(entity.getShortsUuid())
                .build();
    }

    public ShortsBookmarkEntity toShortsBookmarkEntity(ShortsBookmarkResponseDto dto) {
        return ShortsBookmarkEntity.builder()
                .memberUuid(dto.getMemberUuid())
                .shortsUuid(dto.getShortsUuid())
                .build();
    }
}
