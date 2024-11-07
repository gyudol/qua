package com.mulmeong.comment.infrastructure;

import com.mulmeong.comment.common.utils.CursorPage;
import com.mulmeong.comment.dto.out.FeedCommentResponseDto;
import com.mulmeong.comment.entity.FeedComment;
import com.mulmeong.comment.entity.QFeedComment;
import com.querydsl.core.BooleanBuilder;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Repository
@RequiredArgsConstructor
@Slf4j
public class FeedCommentRepositoryCustomImpl implements FeedCommentRepositoryCustom {

    private static final int DEFAULT_PAGE_SIZE = 10;
    private static final int DEFAULT_PAGE_NUMBER = 0;

    private final JPAQueryFactory jpaQueryFactory;

    @Override
    public CursorPage<FeedCommentResponseDto> getFeedComments(
            String feedUuid,
            String sortBy,
            Long lastId,
            Integer pageSize,
            Integer pageNo) {
        QFeedComment feedComment = QFeedComment.feedComment;

        BooleanBuilder builder = new BooleanBuilder();

        // 카테고리 필터 적용
        Optional.ofNullable(feedUuid)
                .ifPresent(code -> builder.and(feedComment.feedUuid.eq(feedUuid)));
        //삭제된 댓글은 조회X
        Optional.of(Boolean.FALSE)
                .ifPresent(code -> builder.and(feedComment.status.eq(Boolean.FALSE)));
        // 마지막 ID 커서 적용
        Optional.ofNullable(lastId)
                .ifPresent(id -> builder.and(feedComment.id.lt(id)));

        // 페이지와 페이지 크기 기본값 설정
        int currentPage = Optional.ofNullable(pageNo).orElse(DEFAULT_PAGE_NUMBER);
        int currentPageSize = Optional.ofNullable(pageSize).orElse(DEFAULT_PAGE_SIZE);

        // offset 계산
        int offset = currentPage == 0 ? 0 : (currentPage - 1) * currentPageSize;

        List<FeedComment> feedComments;
        if (sortBy.equals("latest")) {
            feedComments = jpaQueryFactory
                    .selectFrom(feedComment)
                    .where(builder)
                    .orderBy(feedComment.createdAt.desc())
                    .offset(offset)
                    .limit(currentPageSize + 1)
                    .fetch();
        } else { //todo: 추천순 정렬
            feedComments = null;
        }

        // 다음 페이지의 커서 처리 및 hasNext 여부 판단
        Long nextCursor = null;
        boolean hasNext = false;

        if (feedComments.size() > currentPageSize) {
            hasNext = true;
            feedComments = feedComments.subList(0, currentPageSize);  // 실제 페이지 사이즈 만큼 자르기
            nextCursor = feedComments.get(feedComments.size() - 1).getId();  // 마지막 항목의 ID를 커서로 설정
        }

        List<FeedCommentResponseDto> responseDtos = feedComments.stream().map(FeedCommentResponseDto::toDto).toList();

        // CursorPage 객체 반환
        return new CursorPage<>(responseDtos, nextCursor, hasNext, pageSize, pageNo);

    }

}
