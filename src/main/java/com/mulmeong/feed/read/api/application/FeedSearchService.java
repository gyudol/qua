package com.mulmeong.feed.read.api.application;

import com.mulmeong.feed.read.api.dto.in.FeedSearchRequestDto;
import com.mulmeong.feed.read.api.dto.out.FeedResponseDto;
import com.mulmeong.feed.read.common.utils.CursorPage;

public interface FeedSearchService {

    CursorPage<FeedResponseDto> searchFeeds(FeedSearchRequestDto requestDto);

}
