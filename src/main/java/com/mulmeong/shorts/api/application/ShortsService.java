package com.mulmeong.shorts.api.application;

import com.mulmeong.shorts.api.dto.in.ShortsCreateDto;
import com.mulmeong.shorts.api.dto.in.ShortsHashtagUpdateDto;
import com.mulmeong.shorts.api.dto.in.ShortsInfoUpdateDto;
import com.mulmeong.shorts.api.dto.in.ShortsStatusUpdateDto;

public interface ShortsService {

    void createShorts(ShortsCreateDto requestDto);

    void updateShortsInfo(ShortsInfoUpdateDto requestDto);

    void updateShortsStatus(ShortsStatusUpdateDto requestDto);

    void updateShortsHashtag(ShortsHashtagUpdateDto requestDto);

}
