package com.mulmeong.utility.adapter.out.infrastructure.mongo.repository.bookmarkRepository;

import com.mulmeong.utility.adapter.out.infrastructure.mongo.entity.FeedBookmarkEntity;
import com.mulmeong.utility.adapter.out.infrastructure.mongo.entity.QFeedBookmarkEntity;
import com.mulmeong.utility.adapter.out.infrastructure.mongo.entity.ShortsBookmarkEntity;
import com.mulmeong.utility.adapter.out.infrastructure.mongo.mapper.BookmarkEntityMapper;
import com.mulmeong.utility.application.port.in.dto.BookmarkRequestDto;
import com.mulmeong.utility.application.port.out.BookmarkPort;
import com.mulmeong.utility.application.port.out.dto.FeedBookmarkResponseDto;
import com.mulmeong.utility.application.port.out.dto.ShortsBookmarkResponseDto;
import com.mulmeong.utility.common.exception.BaseException;
import com.mulmeong.utility.common.response.BaseResponseStatus;
import com.mulmeong.utility.common.utils.CursorPage;
import com.querydsl.core.types.dsl.BooleanExpression;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.bson.types.ObjectId;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Repository
@Slf4j
public class BookmarkRepository implements BookmarkPort {

    private final FeedBookmarkMongoRepository feedBookmarkMongoRepository;
    private final ShortsBookmarkMongoRepository shortsBookmarkMongoRepository;
    private final BookmarkEntityMapper bookmarkEntityMapper;
    private final MongoTemplate mongoTemplate;

    @Override
    public void addFeedBookmark(FeedBookmarkResponseDto responseDto) {
        if (feedBookmarkMongoRepository.existsByMemberUuidAndFeedUuid(
                responseDto.getMemberUuid(), responseDto.getFeedUuid())) {
            throw new BaseException(BaseResponseStatus.DUPLICATED_BOOKMARK);
        }
        feedBookmarkMongoRepository.save(bookmarkEntityMapper.toFeedBookmarkEntity(responseDto));
    }

    @Override
    public void deleteFeedBookmark(BookmarkRequestDto bookmarkRequestDto) {
        feedBookmarkMongoRepository.deleteByMemberUuidAndFeedUuid(
                bookmarkRequestDto.getMemberUuid(), bookmarkRequestDto.getBookmarkUuid());
    }

    @Override
    public CursorPage<String> getFeedBookmarks(String memberUuid, String lastId, int pageSize) {
        Pageable pageable = PageRequest.of(0, pageSize, Sort.by(Sort.Direction.DESC, "id"));

        List<FeedBookmarkEntity> entities;
        if (lastId != null) {
            entities = feedBookmarkMongoRepository.findByMemberUuidAndIdLessThanOrderByIdDesc(
                    memberUuid, lastId, pageable);
        } else {
            entities = feedBookmarkMongoRepository.findAllByMemberUuidOrderByIdDesc(
                    memberUuid, pageable);
        }

        List<String> feedUuids = entities.stream()
                .map(FeedBookmarkEntity::getFeedUuid)
                .collect(Collectors.toList());

        boolean hasNext = feedUuids.size() == pageSize;
        String nextCursor = hasNext ? entities.get(entities.size() - 1).getId() : null;

        return CursorPage.<String>builder()
                .content(feedUuids)
                .nextCursor(nextCursor)
                .hasNext(hasNext)
                .pageSize(pageSize)
                .build();
    }

    @Override
    public void addShortsBookmark(ShortsBookmarkResponseDto responseDto) {
        if (shortsBookmarkMongoRepository.existsByMemberUuidAndShortsUuid(
                responseDto.getMemberUuid(), responseDto.getShortsUuid())) {
            throw new BaseException(BaseResponseStatus.DUPLICATED_BOOKMARK);
        }
        shortsBookmarkMongoRepository.save(bookmarkEntityMapper.toShortsBookmarkEntity(responseDto));
    }

    @Override
    public void deleteShortsBookmark(BookmarkRequestDto bookmarkRequestDto) {
        shortsBookmarkMongoRepository.deleteByMemberUuidAndShortsUuid(
                bookmarkRequestDto.getMemberUuid(), bookmarkRequestDto.getBookmarkUuid());
    }

    @Override
    public CursorPage<String> getShortsBookmarks(String memberUuid, String lastId, int pageSize) {
        Pageable pageable = PageRequest.of(0, pageSize, Sort.by(Sort.Direction.DESC, "id"));

        List<ShortsBookmarkEntity> entities;
        if (lastId != null) {
            entities = shortsBookmarkMongoRepository.findByMemberUuidAndIdLessThanOrderByIdDesc(
                    memberUuid, lastId, pageable);
        } else {
            entities = shortsBookmarkMongoRepository.findAllByMemberUuidOrderByIdDesc(
                    memberUuid, pageable);
        }

        List<String> feedUuids = entities.stream()
                .map(ShortsBookmarkEntity::getShortsUuid)
                .collect(Collectors.toList());

        boolean hasNext = feedUuids.size() == pageSize;
        String nextCursor = hasNext ? entities.get(entities.size() - 1).getId() : null;

        return CursorPage.<String>builder()
                .content(feedUuids)
                .nextCursor(nextCursor)
                .hasNext(hasNext)
                .pageSize(pageSize)
                .build();
    }
}
