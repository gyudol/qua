package com.mulmeong.contest.infrastructure;

import com.mulmeong.contest.common.utils.CursorPage;
import com.mulmeong.contest.dto.in.ContestQueryRequestDto;
import com.mulmeong.contest.dto.model.BasePaginationRequestDto;
import com.mulmeong.contest.dto.out.ContestResponseDto;
import com.mulmeong.contest.entity.*;
import com.querydsl.core.BooleanBuilder;
import com.querydsl.core.types.OrderSpecifier;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
@RequiredArgsConstructor
@Slf4j
public class ContestCustomRepository {

    private static final int DEFAULT_PAGE_SIZE = 10;
    private static final int DEFAULT_PAGE_NUMBER = 0;
    private final JPAQueryFactory queryFactory;

    QContest contest = QContest.contest;

    public CursorPage<ContestResponseDto> getContests(ContestQueryRequestDto requestDto) {

        BooleanBuilder builder = new BooleanBuilder();

        builder.and(contest.status.eq(requestDto.isStatus()));

        return getPostsWithPagination(requestDto, builder);
    }

    private CursorPage<ContestResponseDto> getPostsWithPagination(
            BasePaginationRequestDto requestDto, BooleanBuilder builder) {

        Optional.ofNullable(requestDto.getLastId()).ifPresent(id -> builder.and(contest.id.lt(Long.parseLong(id))));

        int curPageNo = Optional.ofNullable(requestDto.getPageNo()).orElse(DEFAULT_PAGE_NUMBER);
        int curPageSize = Optional.ofNullable(requestDto.getPageSize()).orElse(DEFAULT_PAGE_SIZE);
        int offset = Math.max(0, (curPageNo - 1) * curPageSize);

        List<Contest> content = queryFactory.selectFrom(contest)
                .where(builder)
                .orderBy(determineSortOrder(contest, requestDto.getSortType()))
                .offset(offset)
                .limit(curPageSize + 1)
                .fetch();

        String nextCursor = null;
        boolean hasNext = false;

        if (content.size() > curPageSize) {
            hasNext = true;
            nextCursor = content.get(curPageSize).getId().toString();
            content = content.subList(0, curPageSize);
        }

        return new CursorPage<>(content.stream().map(ContestResponseDto::toDto).toList(),
                nextCursor, hasNext, content.size(), requestDto.getPageNo());
    }

    private OrderSpecifier<?> determineSortOrder(QContest post, SortType sortType) {
        return switch (sortType) {
            case LATEST -> post.startDate.desc();
            case OLDEST -> post.startDate.asc();
        };
    }

}
