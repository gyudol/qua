package com.mulmeong.comment.application;

import com.mulmeong.comment.common.exception.BaseException;
import com.mulmeong.comment.common.response.BaseResponseStatus;
import com.mulmeong.comment.common.utils.CursorPage;
import com.mulmeong.comment.dto.in.ShortsRecommentRequestDto;
import com.mulmeong.comment.dto.in.ShortsRecommentUpdateDto;
import com.mulmeong.comment.dto.out.ShortsRecommentResponseDto;
import com.mulmeong.comment.entity.ShortsRecomment;
import com.mulmeong.comment.infrastructure.ShortsRecommentRepository;
import com.mulmeong.comment.infrastructure.ShortsRecommentRepositoryCustom;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@RequiredArgsConstructor
@Service
public class ShortsRecommentServiceImpl implements ShortsRecommentService {

    private final ShortsRecommentRepository shortsRecommentRepository;
    private final ShortsRecommentRepositoryCustom shortsRecommentRepositoryCustom;

    @Override
    public void createShortsRecomment(ShortsRecommentRequestDto requestDto) {
        shortsRecommentRepository.save(requestDto.toEntity());
    }

    @Override
    public void updateShortsRecomment(ShortsRecommentUpdateDto updateDto) {
        ShortsRecomment shortsRecomment = shortsRecommentRepository.findByRecommentUuid(updateDto.getRecommentUuid())
                .orElseThrow(() -> new BaseException(BaseResponseStatus.NO_EXIST_RE_COMMENT)
                );
        shortsRecommentRepository.save(updateDto.toEntity(shortsRecomment));
    }

    @Override
    public void deleteShortsRecomment(String recommentUuid) {
        ShortsRecomment shortsRecomment = shortsRecommentRepository.findByRecommentUuid(recommentUuid)
                .orElseThrow(() -> new BaseException(BaseResponseStatus.NO_EXIST_RE_COMMENT)
                );
        shortsRecommentRepository.delete(shortsRecomment);
    }

    @Override
    public CursorPage<ShortsRecommentResponseDto> getShortsRecommets(
            String commentUuid, Long lastId, Integer pageSize, Integer pageNo) {
        CursorPage<ShortsRecomment> cursorPage = shortsRecommentRepositoryCustom
                .getShortsReomments(commentUuid, lastId, pageSize, pageNo);
        return CursorPage.toCursorPage(cursorPage, cursorPage.getContent().stream()
                .map(ShortsRecommentResponseDto::toDto).toList());
    }
}
