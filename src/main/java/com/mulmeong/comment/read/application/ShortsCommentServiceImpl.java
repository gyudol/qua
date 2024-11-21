package com.mulmeong.comment.read.application;

import com.mulmeong.comment.read.common.exception.BaseException;
import com.mulmeong.comment.read.common.response.BaseResponseStatus;
import com.mulmeong.comment.read.common.utils.CursorPage;
import com.mulmeong.comment.read.dto.out.FeedCommentResponseDto;
import com.mulmeong.comment.read.dto.out.ShortsCommentResponseDto;
import com.mulmeong.comment.read.entity.FeedComment;
import com.mulmeong.comment.read.entity.ShortsComment;
import com.mulmeong.comment.read.infrsatructure.ShortsCommentRepository;
import com.mulmeong.comment.read.infrsatructure.ShortsCommentRepositoryCustom;
import com.mulmeong.event.ShortsCommentCreateEvent;
import com.mulmeong.event.ShortsCommentDeleteEvent;
import com.mulmeong.event.ShortsCommentUpdateEvent;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@RequiredArgsConstructor
@Service
public class ShortsCommentServiceImpl implements ShortsCommentService {

    private final ShortsCommentRepository shortsCommentRepository;
    private final ShortsCommentRepositoryCustom shortsCommentRepositoryCustom;
    private final MongoTemplate mongoTemplate;

    @Override
    public void createShortsComment(ShortsCommentCreateEvent message) {
        ShortsComment shortsComment = new ShortsCommentCreateEvent(message).toEntity();
        shortsCommentRepository.save(shortsComment);
    }

    @Override
    public void updateShortsComment(ShortsCommentUpdateEvent message) {
        ShortsCommentUpdateEvent dto = new ShortsCommentUpdateEvent(message);
        ShortsComment comment = shortsCommentRepository.findByCommentUuid(dto.getCommentUuid()).orElseThrow(
                () -> new BaseException(BaseResponseStatus.NO_EXIST_COMMENT)
        );
        System.out.println(comment);
        ShortsComment updated = dto.toEntity(comment);
        shortsCommentRepository.save(updated);
    }

    @Override
    public void deleteShortsComment(ShortsCommentDeleteEvent message) {
        ShortsCommentDeleteEvent dto = new ShortsCommentDeleteEvent(message);
        ShortsComment comment = shortsCommentRepository.findByCommentUuid(dto.getCommentUuid()).orElseThrow(
                () -> new BaseException(BaseResponseStatus.NO_EXIST_COMMENT)
        );
        ShortsComment deleted = dto.toEntity(comment);
        shortsCommentRepository.save(deleted);
    }

    @Override
    public CursorPage<ShortsCommentResponseDto> getShortsCommentsByPage(
            String shortsUuid, String sortBy, String lastId, Integer pageSize, Integer pageNo) {
        List<String> cursors = shortsCommentRepository.findByShortsUuid(shortsUuid).stream()
                .map(this::buildCursor).toList();
        CursorPage<ShortsComment> cursorPage = shortsCommentRepositoryCustom.getShortsComments(
                shortsUuid, sortBy, lastId, pageSize, pageNo);

        return CursorPage.toCursorPage(cursorPage, cursorPage.getContent().stream()
                .map(ShortsCommentResponseDto::toDto).toList());
    }

    public String buildCursor(ShortsComment comment) {

        String cursor = String.format("%010d", comment.getLikeCount())
                + String.format("%010d", 1000000000 - comment.getDislikeCount())
                + String.format("%010d", comment.getRecommentCount())
                + comment.getId(); // ID 추가

        Query query = new Query(Criteria.where("commentUuid").is(comment.getCommentUuid()));
        Update update = new Update().set("customCursor", cursor);
        mongoTemplate.updateFirst(query, update, FeedComment.class);
        return cursor;
    }
}
