package com.mulmeong.comment.application;

import com.mulmeong.comment.common.exception.BaseException;
import com.mulmeong.comment.common.response.BaseResponseStatus;
import com.mulmeong.comment.common.utils.CursorPage;
import com.mulmeong.comment.dto.in.ShortsCommentDeleteDto;
import com.mulmeong.comment.dto.in.ShortsCommentRequestDto;
import com.mulmeong.comment.dto.in.ShortsCommentUpdateDto;
import com.mulmeong.comment.dto.out.ShortsCommentResponseDto;
import com.mulmeong.comment.entity.ShortsComment;
import com.mulmeong.comment.infrasturcture.ShortsCommentRepository;
import com.mulmeong.comment.infrasturcture.ShortsCommentRepositoryCustom;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class ShortsCommentServiceImpl implements ShortsCommentService {

    private final ShortsCommentRepository shortsCommentRepository;
    private final ShortsCommentRepositoryCustom shortsCommentRepositoryCustom;

    @Override
    public void createFeedComment(ShortsCommentRequestDto requestDto) {
        shortsCommentRepository.save(requestDto.toEntity());
    }

    @Override
    public void updateFeedComment(ShortsCommentUpdateDto updateDto) {
        ShortsComment shortsComment = shortsCommentRepository.findByCommentUuid(updateDto.getCommentUuid()).orElseThrow(
                () -> new BaseException(BaseResponseStatus.NO_EXIST_COMMENT)
        );
        shortsCommentRepository.save(updateDto.toEntity(shortsComment));
    }

    @Override
    public void deleteShortsComment(String commentUuid) {
        ShortsComment shortsComment = shortsCommentRepository.findByCommentUuid(commentUuid).orElseThrow(
                () -> new BaseException(BaseResponseStatus.NO_EXIST_COMMENT)
        );
        shortsCommentRepository.save(ShortsCommentDeleteDto.toEntity(shortsComment));
    }

    @Override
    public List<ShortsCommentResponseDto> getShortsComments(String shortsUuid) {
        List<ShortsComment> feedComments = shortsCommentRepository.findByShortsUuid(shortsUuid);

        return feedComments.stream().map(ShortsCommentResponseDto::toDto).toList();
    }

    @Override
    public CursorPage<ShortsCommentResponseDto> getShortsCommentsByPage(
            String shortsUuid,
            String sortBy,
            Long lastId,
            Integer pageSize,
            Integer pageNo) {

        return shortsCommentRepositoryCustom.getShortsComments(shortsUuid, sortBy, lastId, pageSize, pageNo);
    }
}
