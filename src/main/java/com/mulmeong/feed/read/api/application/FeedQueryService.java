package com.mulmeong.feed.read.api.application;

import com.mulmeong.feed.read.api.dto.FeedResponseDto;

public interface FeedQueryService {

    FeedResponseDto getSingleFeed(String feedUuid);

}
