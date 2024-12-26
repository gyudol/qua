package com.mulmeong.comment.read.infrsatructure;

import com.mulmeong.comment.read.common.utils.CursorPage;
import com.mulmeong.comment.read.entity.FeedRecomment;
import com.mulmeong.comment.read.entity.QFeedRecomment;
import com.querydsl.core.BooleanBuilder;
import lombok.RequiredArgsConstructor;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.repository.support.SpringDataMongodbQuery;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;


@Repository
@RequiredArgsConstructor
public class FeedRecommentRepositoryCustomImpl implements FeedRecommentRepositoryCustom {
    private static final int DEFAULT_PAGE_SIZE = 10;
    private static final int DEFAULT_PAGE_NUMBER = 0;
    private final MongoTemplate mongoTemplate;


    @Override
    public CursorPage<FeedRecomment> getFeedRecomments(
            String commentUuid, String lastId, Integer pageSize, Integer pageNo) {
        QFeedRecomment feedRecomment = QFeedRecomment.feedRecomment;

        BooleanBuilder builder = new BooleanBuilder();

        // 카테고리 필터 적용
        Optional.ofNullable(commentUuid)
                .ifPresent(code -> builder.and(feedRecomment.commentUuid.eq(commentUuid)));

        // 마지막 ID 커서 적용
        Optional.ofNullable(lastId)
                .ifPresent(id -> builder.and(feedRecomment.id.gt(id)));

        // 페이지와 페이지 크기 기본값 설정
        int currentPage = Optional.ofNullable(pageNo).orElse(DEFAULT_PAGE_NUMBER);
        int currentPageSize = Optional.ofNullable(pageSize).orElse(DEFAULT_PAGE_SIZE);

        // offset 계산
        int offset = currentPage == 0 ? 0 : (currentPage - 1) * currentPageSize;

        SpringDataMongodbQuery<FeedRecomment> query = new SpringDataMongodbQuery<>(
                mongoTemplate, FeedRecomment.class);

        List<FeedRecomment> feedRecomments =
                query.where(builder)
                .orderBy(feedRecomment.id.asc())
                .offset(offset)
                .limit(currentPageSize + 1)
                .fetch();

        // 다음 페이지의 커서 처리 및 hasNext 여부 판단
        String nextCursor = null;
        boolean hasNext = feedRecomments.size() > currentPageSize;

        if (hasNext) {
            feedRecomments = feedRecomments.subList(0, currentPageSize);  // 실제 페이지 사이즈 만큼 자르기
            nextCursor = feedRecomments.get(feedRecomments.size() - 1).getId();  // 마지막 항목의 ID를 커서로 설정
        }

        // CursorPage 객체 반환
        return new CursorPage<>(feedRecomments, nextCursor, hasNext, pageSize, pageNo);
    }
}
