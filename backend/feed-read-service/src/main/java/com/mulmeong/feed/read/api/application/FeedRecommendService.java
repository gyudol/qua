package com.mulmeong.feed.read.api.application;

import com.mulmeong.feed.read.api.dto.in.FeedRecommendRequestDto;
import com.mulmeong.feed.read.api.dto.out.FeedResponseDto;
import com.mulmeong.feed.read.common.utils.CursorPage;

public interface FeedRecommendService {

    CursorPage<FeedResponseDto> recommendFeeds(FeedRecommendRequestDto requestDto);

}
