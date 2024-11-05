package com.mulmeong.comment.application;

import com.mulmeong.comment.common.exception.BaseException;
import com.mulmeong.comment.common.response.BaseResponseStatus;
import com.mulmeong.comment.dto.in.ShortsRecommentRequestDto;
import com.mulmeong.comment.dto.in.ShortsRecommentUpdateDto;
import com.mulmeong.comment.entity.ShortsRecomment;
import com.mulmeong.comment.infrastructure.ShortsRecommentRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@RequiredArgsConstructor
@Service
public class ShortsRecommentServiceImpl implements ShortsRecommentService {

    private final ShortsRecommentRepository shortsRecommentRepository;

    @Override
    public void createFeedComment(ShortsRecommentRequestDto requestDto) {
        shortsRecommentRepository.save(requestDto.toEntity());
    }

    @Override
    public void updateFeedComment(ShortsRecommentUpdateDto updateDto) {
        ShortsRecomment shortsRecomment = shortsRecommentRepository.findByRecommentUuid(updateDto.getRecommentUuid())
                .orElseThrow(() -> new BaseException(BaseResponseStatus.NO_EXIST_RE_COMMENT)
        );
        shortsRecommentRepository.save(updateDto.toEntity(shortsRecomment));
    }

    @Override
    public void deleteFeedComment(String recommentUuid) {
        ShortsRecomment shortsRecomment = shortsRecommentRepository.findByRecommentUuid(recommentUuid)
                .orElseThrow(() -> new BaseException(BaseResponseStatus.NO_EXIST_RE_COMMENT)
        );
        shortsRecommentRepository.delete(shortsRecomment);
    }
}
