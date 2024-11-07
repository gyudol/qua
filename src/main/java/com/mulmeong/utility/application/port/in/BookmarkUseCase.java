package com.mulmeong.utility.application.port.in;

import com.mulmeong.utility.application.port.in.dto.BookmarkRequestDto;

import java.util.List;

public interface BookmarkUseCase {

    void addFeedBookmark(BookmarkRequestDto requestDto);

    void deleteFeedBookmark(BookmarkRequestDto bookmarkRequestDto);

    List<String> getFeedBookmarks(String memberUuid);

    void addShortsBookmark(BookmarkRequestDto requestDto);

    void deleteShortsBookmark(BookmarkRequestDto bookmarkRequestDto);
}
