package com.mulmeong.utility.application.service;

import com.mulmeong.utility.application.mapper.BookmarkDtoMapper;
import com.mulmeong.utility.application.port.in.BookmarkUseCase;
import com.mulmeong.utility.application.port.in.dto.BookmarkRequestDto;
import com.mulmeong.utility.application.port.out.BookmarkPort;
import com.mulmeong.utility.application.port.out.dto.BookmarkResponseDto;
import com.mulmeong.utility.application.port.out.dto.DislikeEntityResponseDto;
import com.mulmeong.utility.domain.model.Bookmark;
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
        Bookmark newBookmark = Bookmark.builder()
                .memberUuid(requestDto.getMemberUuid())
                .bookmarkUuid(requestDto.getBookmarkUuid())
                .build();

        bookmarkPort.addFeedBookmark(bookmarkDtoMapper.toDto(newBookmark));
    }

    @Override
    public void deleteFeedBookmark(BookmarkRequestDto bookmarkRequestDto) {
        bookmarkPort.deleteFeedBookmark(bookmarkRequestDto);
    }

    @Override
    public List<String> getFeedBookmarks(String memberUuid) {
        List<BookmarkResponseDto> feedBookmarks = bookmarkPort.getFeedBookmarks(memberUuid);
        return feedBookmarks.stream()
                .map(BookmarkResponseDto::getBookmarkUuid)
                .collect(Collectors.toList());
    }

    @Override
    public void addShortsBookmark(BookmarkRequestDto requestDto) {
        Bookmark newBookmark = Bookmark.builder()
                .memberUuid(requestDto.getMemberUuid())
                .bookmarkUuid(requestDto.getBookmarkUuid())
                .build();

        bookmarkPort.addShortsBookmark(bookmarkDtoMapper.toDto(newBookmark));
    }
}
