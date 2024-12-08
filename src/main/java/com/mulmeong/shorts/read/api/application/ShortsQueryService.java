package com.mulmeong.shorts.read.api.application;

import com.mulmeong.shorts.read.api.dto.in.ShortsAuthorRequestDto;
import com.mulmeong.shorts.read.api.dto.out.ShortsResponseDto;
import com.mulmeong.shorts.read.common.utils.CursorPage;

public interface ShortsQueryService {

    ShortsResponseDto getSingleShorts(String shortsUuid);

    CursorPage<ShortsResponseDto> getShortsByAuthor(ShortsAuthorRequestDto requestDto);

}
