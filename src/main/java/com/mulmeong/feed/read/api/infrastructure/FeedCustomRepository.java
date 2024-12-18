package com.mulmeong.feed.read.api.infrastructure;

import com.mulmeong.feed.read.api.domain.document.Feed;
import com.mulmeong.feed.read.api.domain.document.QFeed;
import com.mulmeong.feed.read.api.domain.model.SortType;
import com.mulmeong.feed.read.api.domain.model.Visibility;
import com.mulmeong.feed.read.api.dto.in.FeedAuthorRequestDto;
import com.mulmeong.feed.read.api.dto.in.FeedFilterRequestDto;
import com.mulmeong.feed.read.api.dto.in.IndexSyncRequestDto;
import com.mulmeong.feed.read.api.dto.model.BasePaginationRequestDto;
import com.mulmeong.feed.read.api.dto.out.FeedResponseDto;
import com.mulmeong.feed.read.common.utils.CursorPage;
import com.querydsl.core.BooleanBuilder;
import com.querydsl.core.types.OrderSpecifier;
import java.util.List;
import java.util.Optional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.repository.support.SpringDataMongodbQuery;
import org.springframework.stereotype.Repository;

@Slf4j
@RequiredArgsConstructor
@Repository
public class FeedCustomRepository {     // QueryDSL Repository

    private static final int DEFAULT_PAGE_SIZE = 20;
    private static final int DEFAULT_PAGE_NUMBER = 1;

    private final MongoTemplate mongoTemplate;
    private final QFeed feed = QFeed.feed;

    public CursorPage<FeedResponseDto> getFeedsByCategoryOrTag(FeedFilterRequestDto requestDto) {

        BooleanBuilder builder = new BooleanBuilder();

        builder.and(feed.visibility.eq(Visibility.VISIBLE));

        Optional.ofNullable(requestDto.getCategoryName()).ifPresent(category ->
            builder.and(feed.categoryName.eq(category)));
        Optional.ofNullable(requestDto.getHashtagName()).ifPresent(hashtag ->
            builder.and(feed.hashtags.any().name.eq(hashtag)));

        return getFeedsWithPagination(requestDto, builder);
    }

    public CursorPage<FeedResponseDto> getFeedsByAuthor(FeedAuthorRequestDto requestDto) {

        BooleanBuilder builder = new BooleanBuilder();

        if (!requestDto.isOwner()) {
            builder.and(feed.visibility.eq(Visibility.VISIBLE));
        }
        Optional.ofNullable(requestDto.getAuthorUuid()).ifPresent(author ->
            builder.and(feed.memberUuid.eq(author)));

        return getFeedsWithPagination(requestDto, builder);
    }

    public List<Feed> getFeedsForSync(IndexSyncRequestDto requestDto) {

        BooleanBuilder builder = new BooleanBuilder();

        Optional.ofNullable(requestDto.getLastId()).ifPresent(id -> builder.and(feed.id.goe(id)));

        SpringDataMongodbQuery<Feed> query = new SpringDataMongodbQuery<>(mongoTemplate,
            Feed.class).where(builder);

        Optional.ofNullable(requestDto.getLimit())
            .filter(limit -> limit > 0)
            .ifPresent(query::limit);

        return query.fetch();
    }

    private CursorPage<FeedResponseDto> getFeedsWithPagination(BasePaginationRequestDto requestDto,
        BooleanBuilder builder) {

        if (requestDto.getSortType().equals(SortType.LATEST)) {
            Optional.ofNullable(requestDto.getLastId())
                .ifPresent(id -> builder.and(feed.id.loe(id)));
        }

        int curPageNo = Optional.ofNullable(requestDto.getPageNo())
            .map(pageNo -> pageNo > 0 ? pageNo - 1 : 0).orElse(DEFAULT_PAGE_NUMBER);
        int curPageSize = Optional.ofNullable(requestDto.getPageSize()).orElse(DEFAULT_PAGE_SIZE);
        int offset = Math.max(0, (curPageNo - 1) * curPageSize);

        log.info("{}", curPageNo);
        SpringDataMongodbQuery<Feed> query = new SpringDataMongodbQuery<>(mongoTemplate,
            Feed.class);

        List<Feed> content = query.where(builder)
            .orderBy(determineSortOrder(feed, requestDto.getSortType()))
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

        return new CursorPage<>(content.stream().map(FeedResponseDto::fromDocument).toList(),
            nextCursor, hasNext, content.size(), curPageNo + 1);
    }

    private OrderSpecifier<?> determineSortOrder(QFeed feed, SortType sortType) {

        return switch (sortType) {
            case LATEST -> feed.createdAt.desc();
            case LIKES -> feed.netLikes.desc();
        };
    }

}
