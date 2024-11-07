package com.mulmeong.utility.application.port.in;

import com.mulmeong.utility.application.port.in.dto.BookmarkRequestDto;

public interface BookmarkUseCase {

    void addFeedBookmark(BookmarkRequestDto requestDto);
}
