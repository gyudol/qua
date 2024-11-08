package com.mulmeong.comment.infrasturcture;

import com.mulmeong.comment.common.utils.CursorPage;
import com.mulmeong.comment.dto.out.ShortsCommentResponseDto;
import com.mulmeong.comment.entity.QShortsComment;
import com.mulmeong.comment.entity.ShortsComment;
import com.querydsl.core.BooleanBuilder;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
@RequiredArgsConstructor
@Slf4j
public class ShortsCommentRepositoryCustomImpl implements ShortsCommentRepositoryCustom {

    private static final int DEFAULT_PAGE_SIZE = 10;
    private static final int DEFAULT_PAGE_NUMBER = 0;

    private final JPAQueryFactory jpaQueryFactory;

    @Override
    public CursorPage<ShortsComment> getShortsComments(
            String shortsUuid,
            String sortBy,
            Long lastId,
            Integer pageSize,
            Integer pageNo) {
        QShortsComment shortsComment = QShortsComment.shortsComment;

        BooleanBuilder builder = new BooleanBuilder();

        Optional.ofNullable(shortsUuid)
                .ifPresent(code -> builder.and(shortsComment.shortsUuid.eq(shortsUuid)));
        //삭제된 댓글은 조회X
        Optional.of(Boolean.FALSE)
                .ifPresent(code -> builder.and(shortsComment.status.eq(Boolean.FALSE)));
        // 마지막 ID 커서 적용
        Optional.ofNullable(lastId)
                .ifPresent(id -> builder.and(shortsComment.id.lt(id)));

        // 페이지와 페이지 크기 기본값 설정
        int currentPage = Optional.ofNullable(pageNo).orElse(DEFAULT_PAGE_NUMBER);
        int currentPageSize = Optional.ofNullable(pageSize).orElse(DEFAULT_PAGE_SIZE);

        // offset 계산
        int offset = currentPage == 0 ? 0 : (currentPage - 1) * currentPageSize;

        List<ShortsComment> shortsComments;
        if (sortBy.equals("latest")) {
            shortsComments = jpaQueryFactory
                    .selectFrom(shortsComment)
                    .where(builder)
                    .orderBy(shortsComment.createdAt.desc())
                    .offset(offset)
                    .limit(currentPageSize + 1)
                    .fetch();
        } else { //todo: 추천순 정렬
            shortsComments = null;
        }

        // 다음 페이지의 커서 처리 및 hasNext 여부 판단
        Long nextCursor = null;
        boolean hasNext = shortsComments.size() > currentPageSize;

        if (hasNext) {
            shortsComments = shortsComments.subList(0, currentPageSize);  // 실제 페이지 사이즈 만큼 자르기
            nextCursor = shortsComments.get(shortsComments.size() - 1).getId();  // 마지막 항목의 ID를 커서로 설정
        }

        // CursorPage 객체 반환
        return new CursorPage<>(shortsComments, nextCursor, hasNext, pageSize, pageNo);
    }
}
