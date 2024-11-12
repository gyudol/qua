package com.mulmeong.feed.api.application;

import com.mulmeong.feed.api.dto.in.CreateFeedRequestDto;

public interface FeedService {

    void createFeed(CreateFeedRequestDto requestDto);



}
