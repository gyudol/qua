package com.mulmeong.shorts.read.api.application;

import static com.mulmeong.shorts.read.common.response.BaseResponseStatus.SHORTS_NOT_FOUND;

import com.mulmeong.shorts.read.api.dto.in.ShortsAuthorRequestDto;
import com.mulmeong.shorts.read.api.dto.out.ShortsResponseDto;
import com.mulmeong.shorts.read.api.infrastructure.ShortsCustomRepository;
import com.mulmeong.shorts.read.api.infrastructure.ShortsQueryRepository;
import com.mulmeong.shorts.read.common.exception.BaseException;
import com.mulmeong.shorts.read.common.utils.CursorPage;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Transactional(readOnly = true)
@RequiredArgsConstructor
@Service
public class ShortsQueryServiceImpl implements ShortsQueryService {

    private final ShortsQueryRepository shortsQueryRepository;
    private final ShortsCustomRepository shortsCustomRepository;

    @Override
    public ShortsResponseDto getSingleShorts(String shortsUuid) {

        return ShortsResponseDto.fromDocument(shortsQueryRepository.findByShortsUuid(shortsUuid)
            .orElseThrow(() -> new BaseException(SHORTS_NOT_FOUND)));
    }

    @Override
    public CursorPage<ShortsResponseDto> getShortsByAuthor(ShortsAuthorRequestDto requestDto) {

        return shortsCustomRepository.getShortsByAuthor(requestDto);
    }

}
