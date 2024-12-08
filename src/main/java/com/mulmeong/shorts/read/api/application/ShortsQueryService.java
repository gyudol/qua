package com.mulmeong.shorts.read.api.application;

import com.mulmeong.shorts.read.api.dto.out.ShortsResponseDto;

public interface ShortsQueryService {

    ShortsResponseDto getSingleShorts(String shortsUuid);

}
