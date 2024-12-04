package com.mulmeong.shorts.api.application;

import com.mulmeong.shorts.api.dto.in.ShortsCreateDto;

public interface ShortsService {

    void createShorts(ShortsCreateDto requestDto);

}
