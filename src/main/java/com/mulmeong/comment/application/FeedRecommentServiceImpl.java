package com.mulmeong.comment.application;

import com.mulmeong.comment.common.exception.BaseException;
import com.mulmeong.comment.common.response.BaseResponseStatus;
import com.mulmeong.comment.common.utils.CursorPage;
import com.mulmeong.comment.dto.in.FeedRecommentRequestDto;
import com.mulmeong.comment.dto.in.FeedRecommentUpdateDto;
import com.mulmeong.comment.dto.out.FeedRecommentResponseDto;
import com.mulmeong.comment.entity.FeedRecomment;
import com.mulmeong.comment.infrastructure.FeedCommentRepository;
import com.mulmeong.comment.infrastructure.FeedRecommentRepository;
import com.mulmeong.comment.infrastructure.FeedRecommentRepositoryCustom;
import com.mulmeong.event.FeedCommentDeleteEventDto;
import com.mulmeong.event.FeedRecommentCreateEventDto;
import com.mulmeong.event.FeedRecommentDeleteEventDto;
import com.mulmeong.event.FeedRecommentUpdateEventDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Slf4j
@RequiredArgsConstructor
@Service
public class FeedRecommentServiceImpl implements FeedRecommentService {

    private final FeedCommentRepository feedCommentRepository;
    private final FeedRecommentRepository feedRecommentRepository;
    private final FeedRecommentRepositoryCustom feedRecommentRepositoryCustom;
    private final EventPublisher eventPublisher;

    @Override
    @Transactional
    public void createFeedRecomment(FeedRecommentRequestDto requestDto) {
        if (!feedCommentRepository.existsByCommentUuid(requestDto.getCommentUuid())) {
            throw new BaseException(BaseResponseStatus.NO_EXIST_COMMENT);
        }
        FeedRecomment feedRecomment = feedRecommentRepository.save(requestDto.toEntity());
        eventPublisher.send("feed-recomment-create", FeedRecommentCreateEventDto.toDto(feedRecomment));
    }

    @Override
    @Transactional
    public void updateFeedRecomment(FeedRecommentUpdateDto updateDto) {
        FeedRecomment feedRecomment = feedRecommentRepository.findByRecommentUuid(updateDto.getRecommentUuid())
                .orElseThrow(() -> new BaseException(BaseResponseStatus.NO_EXIST_RECOMMENT));
        if (!(feedRecomment.getMemberUuid().equals(updateDto.getMemberUuid()))) {
            throw new BaseException(BaseResponseStatus.NO_UPDATE_RECOMMENT_AUTHORITY);
        }
        eventPublisher.send("feed-recomment-update", FeedRecommentUpdateEventDto.toDto(feedRecomment));
        feedRecommentRepository.save(updateDto.toEntity(feedRecomment));
    }

    @Override
    @Transactional
    public void deleteFeedRecomment(String memberUuid, String recommentUuid) {
        FeedRecomment feedRecomment = feedRecommentRepository.findByRecommentUuid(recommentUuid)
                .orElseThrow(() -> new BaseException(BaseResponseStatus.NO_EXIST_RECOMMENT));
        if (!feedRecomment.getMemberUuid().equals(memberUuid)) {
            throw new BaseException(BaseResponseStatus.NO_DELETE_RECOMMENT_AUTHORITY);
        }
        eventPublisher.send("feed-recomment-delete", FeedRecommentDeleteEventDto.toDto(recommentUuid));
        feedRecommentRepository.delete(feedRecomment);

    }

    @Override
    public FeedRecommentResponseDto getFeedRecomment(String recommentUuid) {
        FeedRecomment feedRecomment = feedRecommentRepository.findByRecommentUuid(recommentUuid)
                .orElseThrow(() -> new BaseException(BaseResponseStatus.NO_EXIST_RECOMMENT));
        return FeedRecommentResponseDto.toDto(feedRecomment);
    }

    @Override
    public CursorPage<FeedRecommentResponseDto> getFeedRecomments(
            String commentUuid, Long lastId, Integer pageSize, Integer pageNo) {
        CursorPage<FeedRecomment> cursorPage = feedRecommentRepositoryCustom
                .getFeedRecomments(commentUuid, lastId, pageSize, pageNo);

        return CursorPage.toCursorPage(cursorPage, cursorPage.getContent().stream()
                .map(FeedRecommentResponseDto::toDto).toList());
    }
}
