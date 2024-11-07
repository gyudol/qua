package com.mulmeong.utility.application.port.out;

import com.mulmeong.utility.application.port.in.dto.BookmarkRequestDto;
import com.mulmeong.utility.application.port.out.dto.BookmarkResponseDto;

public interface BookmarkPort {

    void addFeedBookmark(BookmarkResponseDto responseDto);

    void deleteFeedBookmark(BookmarkRequestDto bookmarkRequestDto);
}
