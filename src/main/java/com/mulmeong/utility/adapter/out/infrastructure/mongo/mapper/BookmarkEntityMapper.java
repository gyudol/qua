package com.mulmeong.utility.adapter.out.infrastructure.mongo.mapper;

import com.mulmeong.utility.adapter.out.infrastructure.mongo.entity.BookmarkEntity;
import com.mulmeong.utility.adapter.out.infrastructure.mongo.entity.DislikeEntity;
import com.mulmeong.utility.application.port.out.dto.BookmarkResponseDto;
import com.mulmeong.utility.application.port.out.dto.DislikeResponseDto;
import org.springframework.stereotype.Component;

@Component
public class BookmarkEntityMapper {

    public BookmarkEntity toEntity(BookmarkResponseDto dto) {
        return BookmarkEntity.builder()
                .memberUuid(dto.getMemberUuid())
                .bookmarkUuid(dto.getBookmarkUuid())
                .build();
    }

}
