package com.mulmeong.utility.application.mapper;

import com.mulmeong.utility.application.port.out.dto.FeedBookmarkResponseDto;
import com.mulmeong.utility.application.port.out.dto.ShortsBookmarkResponseDto;
import com.mulmeong.utility.domain.model.FeedBookmark;
import com.mulmeong.utility.domain.model.ShortsBookmark;
import org.springframework.stereotype.Component;

@Component
public class BookmarkDtoMapper {

    public FeedBookmarkResponseDto toFeedDto(FeedBookmark feedBookmark) {
        return FeedBookmarkResponseDto.builder()
                .id(feedBookmark.getId())
                .memberUuid(feedBookmark.getMemberUuid())
                .feedUuid(feedBookmark.getFeedUuid())
                .build();
    }

    public ShortsBookmarkResponseDto toShortsDto(ShortsBookmark shortsBookmark) {
        return ShortsBookmarkResponseDto.builder()
                .id(shortsBookmark.getId())
                .memberUuid(shortsBookmark.getMemberUuid())
                .shortsUuid(shortsBookmark.getShortsUuid())
                .build();
    }

}
