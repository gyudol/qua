package com.mulmeong.comment.infrastructure;

import com.mulmeong.comment.common.utils.CursorPage;
import com.mulmeong.comment.dto.out.ShortsRecommentResponseDto;
import com.mulmeong.comment.entity.QShortsRecomment;
import com.mulmeong.comment.entity.ShortsRecomment;
import com.querydsl.core.BooleanBuilder;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
@RequiredArgsConstructor
public class ShortsRecommentRepositoryCustomImpl implements ShortsRecommentRepositoryCustom {
    private static final int DEFAULT_PAGE_SIZE = 10;
    private static final int DEFAULT_PAGE_NUMBER = 0;

    private final JPAQueryFactory jpaQueryFactory;

    @Override
    public CursorPage<ShortsRecomment> getShortsComments(
            String commentUuid,
            Long lastId,
            Integer pageSize,
            Integer pageNo) {

        QShortsRecomment shortsRecomment = QShortsRecomment.shortsRecomment;

        BooleanBuilder builder = new BooleanBuilder();

        // 카테고리 필터 적용
        Optional.ofNullable(commentUuid)
                .ifPresent(code -> builder.and(shortsRecomment.commentUuid.eq(commentUuid)));

        // 마지막 ID 커서 적용
        Optional.ofNullable(lastId)
                .ifPresent(id -> builder.and(shortsRecomment.id.lt(id)));

        // 페이지와 페이지 크기 기본값 설정
        int currentPage = Optional.ofNullable(pageNo).orElse(DEFAULT_PAGE_NUMBER);
        int currentPageSize = Optional.ofNullable(pageSize).orElse(DEFAULT_PAGE_SIZE);

        // offset 계산
        int offset = currentPage == 0 ? 0 : (currentPage - 1) * currentPageSize;

        List<ShortsRecomment> shortsRecomments = jpaQueryFactory
                .selectFrom(shortsRecomment)
                .where(builder)
                .orderBy(shortsRecomment.createdAt.desc())
                .offset(offset)
                .limit(currentPageSize + 1)
                .fetch();

        // 다음 페이지의 커서 처리 및 hasNext 여부 판단
        Long nextCursor = null;
        boolean hasNext = shortsRecomments.size() > currentPageSize;

        if (hasNext) {
            shortsRecomments = shortsRecomments.subList(0, currentPageSize);  // 실제 페이지 사이즈 만큼 자르기
            nextCursor = shortsRecomments.get(shortsRecomments.size() - 1).getId();  // 마지막 항목의 ID를 커서로 설정
        }

        // CursorPage 객체 반환
        return new CursorPage<>(shortsRecomments, nextCursor, hasNext, pageSize, pageNo);
    }

}
