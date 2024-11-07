package com.mulmeong.utility.application.port.out;

import com.mulmeong.utility.application.port.out.dto.BookmarkResponseDto;

public interface BookmarkPort {

    void addFeedBookmark(BookmarkResponseDto responseDto);
}
