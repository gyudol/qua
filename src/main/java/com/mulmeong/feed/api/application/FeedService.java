package com.mulmeong.feed.api.application;

import com.mulmeong.feed.api.dto.in.FeedCreateDto;
import com.mulmeong.feed.api.dto.in.FeedHashtagUpdateDto;
import com.mulmeong.feed.api.dto.in.FeedUpdateDto;
import com.mulmeong.feed.api.dto.in.FeedStatusUpdateDto;
import com.mulmeong.feed.api.dto.out.FeedResponseDto;

public interface FeedService {

    void createFeed(FeedCreateDto requestDto);

    FeedResponseDto getFeedDetail(String feedUuid);

    void updateFeed(FeedUpdateDto requestDto);

    void updateFeedStatus(FeedStatusUpdateDto requestDto);

    void updateFeedHashtag(FeedHashtagUpdateDto requestDto);

    void deleteFeed(String feedUuid);

}
