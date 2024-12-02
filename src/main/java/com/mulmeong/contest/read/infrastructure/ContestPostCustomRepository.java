package com.mulmeong.contest.read.infrastructure;

import com.mulmeong.contest.read.common.utils.CursorPage;
import com.mulmeong.contest.read.dto.in.ContestPostRequestDto;
import com.mulmeong.contest.read.dto.model.BasePaginationRequestDto;
import com.mulmeong.contest.read.dto.out.ContestPostResponseDto;
import com.mulmeong.contest.read.entity.ContestPost;
import com.mulmeong.contest.read.entity.QContestPost;
import com.mulmeong.contest.read.entity.SortType;
import com.querydsl.core.BooleanBuilder;
import com.querydsl.core.types.OrderSpecifier;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.repository.support.SpringDataMongodbQuery;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
@RequiredArgsConstructor
@Slf4j
public class ContestPostCustomRepository {

    private static final int DEFAULT_PAGE_SIZE = 10;
    private static final int DEFAULT_PAGE_NUMBER = 0;
    private final MongoTemplate mongoTemplate;

    QContestPost contestPost = QContestPost.contestPost;

    public CursorPage<ContestPostResponseDto> getContestPosts(ContestPostRequestDto requestDto) {

        BooleanBuilder builder = new BooleanBuilder();

        return getPostsWithPagination(requestDto, builder);
    }


    private CursorPage<ContestPostResponseDto> getPostsWithPagination(
            BasePaginationRequestDto requestDto, BooleanBuilder builder) {

        Optional.ofNullable(requestDto.getLastId()).ifPresent(id -> builder.and(contestPost.id.lt(id)));

        int curPageNo = Optional.ofNullable(requestDto.getPageNo()).orElse(DEFAULT_PAGE_NUMBER);
        int curPageSize = Optional.ofNullable(requestDto.getPageSize()).orElse(DEFAULT_PAGE_SIZE);
        int offset = Math.max(0, (curPageNo - 1) * curPageSize);

        SpringDataMongodbQuery<ContestPost> query = new SpringDataMongodbQuery<>(mongoTemplate,
                ContestPost.class);

        List<ContestPost> content = query.where(builder)
                .orderBy(determineSortOrder(contestPost, requestDto.getSortType()))
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

        return new CursorPage<>(content.stream().map(ContestPostResponseDto::toDto).toList(),
                nextCursor, hasNext, content.size(), requestDto.getPageNo());
    }


    private OrderSpecifier<?> determineSortOrder(QContestPost post, SortType sortType) {

        return switch (sortType) {
            case LATEST -> post.createdAt.desc();
            case VOTES -> post.voteCount.desc();
        };
    }
}
