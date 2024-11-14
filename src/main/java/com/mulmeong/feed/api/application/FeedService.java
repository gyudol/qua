package com.mulmeong.feed.api.application;

import com.mulmeong.feed.api.dto.in.CreateFeedRequestDto;
import com.mulmeong.feed.api.dto.in.UpdateFeedRequestDto;
import com.mulmeong.feed.api.dto.in.UpdateFeedStatusRequestDto;
import com.mulmeong.feed.api.dto.out.FeedResponseDto;

public interface FeedService {

    void createFeed(CreateFeedRequestDto requestDto);

    FeedResponseDto getFeedDetail(String feedUuid);

    void updateFeed(UpdateFeedRequestDto requestDto);

    void updateFeedStatus(UpdateFeedStatusRequestDto requestDto);

    void deleteFeed(String feedUuid, String memberUuid);

}
