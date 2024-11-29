package com.mulmeong.feed.read.api.application;

import com.mulmeong.feed.read.api.dto.in.FeedFilterRequestDto;
import com.mulmeong.feed.read.api.dto.out.FeedResponseDto;
import com.mulmeong.feed.read.common.utils.CursorPage;
import java.util.List;

public interface FeedQueryService {

    FeedResponseDto getSingleFeed(String feedUuid);

    CursorPage<FeedResponseDto> getFeedsByCategoryOrTag(FeedFilterRequestDto requestDto);

}
