package com.mulmeong.utility.application.service;

import com.mulmeong.utility.application.mapper.BookmarkDtoMapper;
import com.mulmeong.utility.application.port.in.BookmarkUseCase;
import com.mulmeong.utility.application.port.in.dto.BookmarkRequestDto;
import com.mulmeong.utility.application.port.out.BookmarkPort;
import com.mulmeong.utility.application.port.out.dto.FeedBookmarkResponseDto;
import com.mulmeong.utility.common.utils.CursorPage;
import com.mulmeong.utility.domain.model.FeedBookmark;
import com.mulmeong.utility.domain.model.ShortsBookmark;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@RequiredArgsConstructor
@Service
public class BookmarkService implements BookmarkUseCase {
    private final BookmarkPort bookmarkPort;
    private final BookmarkDtoMapper bookmarkDtoMapper;

    @Override
    public void addFeedBookmark(BookmarkRequestDto requestDto) {
        FeedBookmark newBookmark = FeedBookmark.builder()
                .memberUuid(requestDto.getMemberUuid())
                .feedUuid(requestDto.getBookmarkUuid())
                .build();

        bookmarkPort.addFeedBookmark(bookmarkDtoMapper.toFeedDto(newBookmark));
    }

    @Override
    public void deleteFeedBookmark(BookmarkRequestDto bookmarkRequestDto) {
        bookmarkPort.deleteFeedBookmark(bookmarkRequestDto);
    }

    //todo get
    @Override
    public CursorPage<String> getFeedBookmarks(String memberUuid, String lastId, int pageSize) {
        return bookmarkPort.getFeedBookmarks(memberUuid, lastId, pageSize);
    }

    @Override
    public void addShortsBookmark(BookmarkRequestDto requestDto) {
        ShortsBookmark newBookmark = ShortsBookmark.builder()
                .memberUuid(requestDto.getMemberUuid())
                .shortsUuid(requestDto.getBookmarkUuid())
                .build();

        bookmarkPort.addShortsBookmark(bookmarkDtoMapper.toShortsDto(newBookmark));
    }

    @Override
    public void deleteShortsBookmark(BookmarkRequestDto bookmarkRequestDto) {
        bookmarkPort.deleteShortsBookmark(bookmarkRequestDto);
    }

    //todo get
}
