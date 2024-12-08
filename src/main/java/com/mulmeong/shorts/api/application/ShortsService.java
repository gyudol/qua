package com.mulmeong.shorts.api.application;

import com.mulmeong.shorts.api.dto.in.ShortsCreateDto;
import com.mulmeong.shorts.api.dto.in.ShortsInfoUpdateDto;

public interface ShortsService {

    void createShorts(ShortsCreateDto requestDto);

    void updateShortsInfo(ShortsInfoUpdateDto requestDto);

}
