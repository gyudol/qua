package com.mulmeong.comment.application;

import com.mulmeong.comment.common.exception.BaseException;
import com.mulmeong.comment.common.response.BaseResponseStatus;
import com.mulmeong.comment.common.utils.CursorPage;
import com.mulmeong.comment.dto.in.FeedCommentDeleteDto;
import com.mulmeong.comment.dto.in.FeedCommentRequestDto;
import com.mulmeong.comment.dto.in.FeedCommentUpdateDto;
import com.mulmeong.comment.dto.out.FeedCommentResponseDto;
import com.mulmeong.comment.entity.FeedComment;
import com.mulmeong.comment.infrastructure.FeedCommentRepository;
import com.mulmeong.comment.infrastructure.FeedCommentRepositoryCustom;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


@Slf4j
@RequiredArgsConstructor
@Service
public class FeedCommentServiceImpl implements FeedCommentService {
    private final FeedCommentRepository feedCommentRepository;
    private final FeedCommentRepositoryCustom feedCommentRepositoryCustom;
    //private final KafkaProducer kafkaProducer;

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void createFeedComment(FeedCommentRequestDto requestDto) {
        //FeedComment feedComment =
        feedCommentRepository.save(requestDto.toEntity());
        //kafkaProducer.sendFeedCommentMessage(FeedCommentMessageDto.toCommentDto(feedComment));
    }

    @Override
    public void updateFeedComment(FeedCommentUpdateDto updateDto) {
        FeedComment feedComment = feedCommentRepository.findByCommentUuid(updateDto.getCommentUuid()).orElseThrow(
                () -> new BaseException(BaseResponseStatus.NO_EXIST_COMMENT));
        if (!(feedComment.getMemberUuid().equals(updateDto.getMemberUuid()))) {
            throw new BaseException(BaseResponseStatus.NO_UPDATE_COMMENT_AUTHORITY);
        }
        feedCommentRepository.save(updateDto.toEntity(feedComment));
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void deleteFeedComment(String memberUuid, String commentUuid) {
        FeedComment feedComment = feedCommentRepository.findByCommentUuid(commentUuid).orElseThrow(
                () -> new BaseException(BaseResponseStatus.NO_EXIST_COMMENT));
        if (!(feedComment.getMemberUuid().equals(memberUuid))) {
            throw new BaseException(BaseResponseStatus.NO_DELETE_COMMENT_AUTHORITY);
        }
        feedCommentRepository.save(FeedCommentDeleteDto.toEntity(feedComment));
    }

    @Override
    public FeedCommentResponseDto getFeedComment(String commentUuid) {
        FeedComment feedComment = feedCommentRepository.findByCommentUuid(commentUuid).orElseThrow(
                () -> new BaseException(BaseResponseStatus.NO_EXIST_COMMENT));
        if (!feedComment.isStatus()) {
            throw new BaseException(BaseResponseStatus.NO_EXIST_COMMENT);
        }
        return FeedCommentResponseDto.toDto(feedComment);
    }


    @Override
    public CursorPage<FeedCommentResponseDto> getFeedCommentsByPage(
            String feedUuid,
            String sortBy,
            Long lastId,
            Integer pageSize,
            Integer pageNo) {

        CursorPage<FeedComment> cursorPage = feedCommentRepositoryCustom.getFeedComments(
                feedUuid, sortBy, lastId, pageSize, pageNo);

        return CursorPage.toCursorPage(cursorPage, cursorPage.getContent().stream()
                .map(FeedCommentResponseDto::toDto).toList());
    }

}
