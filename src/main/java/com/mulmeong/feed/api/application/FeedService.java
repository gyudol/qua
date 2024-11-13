package com.mulmeong.feed.api.application;

import com.mulmeong.feed.api.dto.in.CreateFeedRequestDto;
import com.mulmeong.feed.api.dto.in.UpdateFeedRequestDto;

public interface FeedService {

    void createFeed(CreateFeedRequestDto requestDto);
    void updateFeed(UpdateFeedRequestDto requestDto);

}
