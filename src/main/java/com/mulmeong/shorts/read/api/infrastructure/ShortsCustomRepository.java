package com.mulmeong.shorts.read.api.infrastructure;

import static com.mulmeong.shorts.read.api.domain.model.SortType.LIKES;

import com.mulmeong.shorts.read.api.domain.document.QShorts;
import com.mulmeong.shorts.read.api.domain.document.Shorts;
import com.mulmeong.shorts.read.api.domain.model.SortType;
import com.mulmeong.shorts.read.api.domain.model.Visibility;
import com.mulmeong.shorts.read.api.dto.in.ShortsAuthorRequestDto;
import com.mulmeong.shorts.read.api.dto.in.ShortsRecommendationRequestDto;
import com.mulmeong.shorts.read.api.dto.model.BasePaginationRequestDto;
import com.mulmeong.shorts.read.api.dto.out.ShortsResponseDto;
import com.mulmeong.shorts.read.common.utils.CursorPage;
import com.querydsl.core.BooleanBuilder;
import com.querydsl.core.types.OrderSpecifier;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import lombok.RequiredArgsConstructor;
import org.springframework.data.mongodb.core.MongoTemplate;

import org.springframework.data.mongodb.core.aggregation.Aggregation;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.repository.support.SpringDataMongodbQuery;
import org.springframework.stereotype.Repository;

@RequiredArgsConstructor
@Repository
public class ShortsCustomRepository {     // QueryDSL Repository

    private static final int DEFAULT_PAGE_SIZE = 20;
    private static final int DEFAULT_PAGE_NUMBER = 0;

    private final MongoTemplate mongoTemplate;
    private final QShorts shorts = QShorts.shorts;

    public CursorPage<ShortsResponseDto> getShortsByAuthor(ShortsAuthorRequestDto requestDto) {

        BooleanBuilder builder = new BooleanBuilder();

        if (!requestDto.getRequesterUuid().equals(requestDto.getAuthorUuid())) {
            builder.and(shorts.visibility.eq(Visibility.VISIBLE));
        }
        Optional.ofNullable(requestDto.getAuthorUuid()).ifPresent(author ->
            builder.and(shorts.memberUuid.eq(author)));

        return getShortsWithPagination(requestDto, builder);
    }

    public CursorPage<ShortsResponseDto> getRecommendedShorts(
        ShortsRecommendationRequestDto requestDto) {

        BooleanBuilder builder = new BooleanBuilder().and(shorts.visibility.eq(Visibility.VISIBLE));

        int curPageNo = Optional.ofNullable(requestDto.getPageNo()).orElse(DEFAULT_PAGE_NUMBER);
        int curPageSize = Optional.ofNullable(requestDto.getPageSize()).orElse(DEFAULT_PAGE_SIZE);
        List<Shorts> content = new ArrayList<>();

        if (requestDto.isMember()) {
            Aggregation aggregation = Aggregation.newAggregation(
                Aggregation.match(new Criteria("visibility").is(Visibility.VISIBLE)),
                Aggregation.sample(curPageSize));

            content = mongoTemplate.aggregate(aggregation, "shorts", Shorts.class)
                .getMappedResults();
        } else {
            int offset = Math.max(0, (curPageNo - 1) * curPageSize);
            SpringDataMongodbQuery<Shorts> query = new SpringDataMongodbQuery<>(mongoTemplate,
                Shorts.class);

            content = query.where(builder)
                .orderBy(determineSortOrder(shorts, LIKES))
                .offset(offset)
                .limit(curPageSize + 1)
                .fetch();
        }

        String nextCursor = null;
        boolean hasNext = false;

        if (content.size() > curPageSize) {
            hasNext = true;
            nextCursor = content.get(curPageSize).getId();
            content = content.subList(0, curPageSize);
        }

        return new CursorPage<>(content.stream().map(ShortsResponseDto::fromDocument).toList(),
            nextCursor, hasNext, content.size(), curPageNo);
    }

    private CursorPage<ShortsResponseDto> getShortsWithPagination(
        BasePaginationRequestDto requestDto, BooleanBuilder builder) {

        Optional.ofNullable(requestDto.getLastId()).ifPresent(id -> builder.and(shorts.id.lt(id)));

        int curPageNo = Optional.ofNullable(requestDto.getPageNo()).orElse(DEFAULT_PAGE_NUMBER);
        int curPageSize = Optional.ofNullable(requestDto.getPageSize()).orElse(DEFAULT_PAGE_SIZE);
        int offset = Math.max(0, (curPageNo - 1) * curPageSize);

        SpringDataMongodbQuery<Shorts> query = new SpringDataMongodbQuery<>(mongoTemplate,
            Shorts.class);

        List<Shorts> content = query.where(builder)
            .orderBy(determineSortOrder(shorts, requestDto.getSortType()))
            .offset(offset)
            .limit(curPageSize + 1)
            .fetch();

        String nextCursor = null;
        boolean hasNext = false;

        if (content.size() > curPageSize) {
            hasNext = true;
            nextCursor = content.get(curPageSize).getId();
            content = content.subList(0, curPageSize);
        }

        return new CursorPage<>(content.stream().map(ShortsResponseDto::fromDocument).toList(),
            nextCursor, hasNext, content.size(), curPageNo);
    }

    private OrderSpecifier<?> determineSortOrder(QShorts shorts, SortType sortType) {

        return switch (sortType) {
            case LATEST -> shorts.createdAt.desc();
            case LIKES -> shorts.netLikes.desc();
        };
    }

}
