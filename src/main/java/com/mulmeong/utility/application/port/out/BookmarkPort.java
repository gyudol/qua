package com.mulmeong.utility.application.port.out;

import com.mulmeong.utility.application.port.in.dto.BookmarkRequestDto;
import com.mulmeong.utility.application.port.out.dto.FeedBookmarkResponseDto;
import com.mulmeong.utility.application.port.out.dto.ShortsBookmarkResponseDto;
import com.mulmeong.utility.common.utils.CursorPage;

import java.util.List;

public interface BookmarkPort {

    void addFeedBookmark(FeedBookmarkResponseDto responseDto);

    void deleteFeedBookmark(BookmarkRequestDto bookmarkRequestDto);

    CursorPage<String> getFeedBookmarks(String memberUuid, String lastId, int pageSize);

    void addShortsBookmark(ShortsBookmarkResponseDto responseDto);

    void deleteShortsBookmark(BookmarkRequestDto bookmarkRequestDto);

}
