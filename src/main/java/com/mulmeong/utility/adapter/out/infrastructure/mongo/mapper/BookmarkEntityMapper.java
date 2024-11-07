package com.mulmeong.utility.adapter.out.infrastructure.mongo.mapper;

import com.mulmeong.utility.adapter.out.infrastructure.mongo.entity.FeedBookmarkEntity;
import com.mulmeong.utility.application.port.out.dto.BookmarkResponseDto;
import org.springframework.stereotype.Component;

@Component
public class BookmarkEntityMapper {

    public FeedBookmarkEntity toEntity(BookmarkResponseDto dto) {
        return FeedBookmarkEntity.builder()
                .memberUuid(dto.getMemberUuid())
                .feedUuid(dto.getBookmarkUuid())
                .build();
    }

}
