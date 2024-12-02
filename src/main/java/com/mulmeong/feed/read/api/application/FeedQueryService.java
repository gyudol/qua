package com.mulmeong.feed.read.api.application;

import com.mulmeong.feed.read.api.dto.in.FeedAuthorRequestDto;
import com.mulmeong.feed.read.api.dto.in.FeedFilterRequestDto;
import com.mulmeong.feed.read.api.dto.out.FeedResponseDto;
import com.mulmeong.feed.read.common.utils.CursorPage;

public interface FeedQueryService {

    FeedResponseDto getSingleFeed(String feedUuid);

    CursorPage<FeedResponseDto> getFeedsByCategoryOrTag(FeedFilterRequestDto requestDto);

    CursorPage<FeedResponseDto> getFeedsByAuthor(FeedAuthorRequestDto requestDto);

}
