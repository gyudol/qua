package com.mulmeong.utility.application.port.in;

import com.mulmeong.utility.application.port.in.dto.BookmarkRequestDto;
import com.mulmeong.utility.application.port.out.dto.FeedBookmarkResponseDto;
import com.mulmeong.utility.common.utils.CursorPage;

import java.util.List;

public interface BookmarkUseCase {

    void addFeedBookmark(BookmarkRequestDto requestDto);

    void deleteFeedBookmark(BookmarkRequestDto bookmarkRequestDto);

    //todo get
    CursorPage<String> getFeedBookmarks(String memberUuid, String lastId, int pageSize);

    void addShortsBookmark(BookmarkRequestDto requestDto);

    void deleteShortsBookmark(BookmarkRequestDto bookmarkRequestDto);

    //todo get
}
