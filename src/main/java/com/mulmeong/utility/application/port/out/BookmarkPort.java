package com.mulmeong.utility.application.port.out;

import com.mulmeong.utility.application.port.in.dto.BookmarkRequestDto;
import com.mulmeong.utility.application.port.out.dto.BookmarkResponseDto;

import java.util.List;

public interface BookmarkPort {

    void addFeedBookmark(BookmarkResponseDto responseDto);

    void deleteFeedBookmark(BookmarkRequestDto bookmarkRequestDto);

    List<BookmarkResponseDto> getFeedBookmarks(String memberUuid);

    void addShortsBookmark(BookmarkResponseDto responseDto);

    void deleteShortsBookmark(BookmarkRequestDto bookmarkRequestDto);

    List<BookmarkResponseDto> getShortsBookmarks(String memberUuid);
}
