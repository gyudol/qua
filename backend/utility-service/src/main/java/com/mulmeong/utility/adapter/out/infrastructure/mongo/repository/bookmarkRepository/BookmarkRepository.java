package com.mulmeong.utility.adapter.out.infrastructure.mongo.repository.bookmarkRepository;

import com.mulmeong.utility.adapter.out.infrastructure.mongo.entity.FeedBookmarkEntity;
import com.mulmeong.utility.adapter.out.infrastructure.mongo.entity.ShortsBookmarkEntity;
import com.mulmeong.utility.adapter.out.infrastructure.mongo.mapper.BookmarkEntityMapper;
import com.mulmeong.utility.application.port.in.dto.BookmarkRequestDto;
import com.mulmeong.utility.application.port.out.BookmarkPort;
import com.mulmeong.utility.application.port.out.dto.FeedBookmarkResponseDto;
import com.mulmeong.utility.application.port.out.dto.ShortsBookmarkResponseDto;
import com.mulmeong.utility.common.exception.BaseException;
import com.mulmeong.utility.common.response.BaseResponseStatus;
import com.mulmeong.utility.common.utils.CursorPage;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

@RequiredArgsConstructor
@Repository
@Slf4j
public class BookmarkRepository implements BookmarkPort {

    private final FeedBookmarkMongoRepository feedBookmarkMongoRepository;
    private final ShortsBookmarkMongoRepository shortsBookmarkMongoRepository;
    private final FeedBookmarkMongoRepositoryCustom feedBookmarkMongoRepositoryCustom;
    private final ShortsBookmarkMongoRepositoryCustom shortsBookmarkMongoRepositoryCustom;
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
    public CursorPage<String> getFeedBookmarks(String memberUuid, String lastId, int pageSize, int pageNo) {
        CursorPage<FeedBookmarkEntity> cursorPage = feedBookmarkMongoRepositoryCustom.getFeedBookmarks(
                memberUuid, lastId, pageSize, pageNo);

        List<String> feedUuids = cursorPage.getContent().stream()
                .map(FeedBookmarkEntity::getFeedUuid)
                .toList();

        return CursorPage.toCursorPage(cursorPage, feedUuids);
    }

    @Override
    public boolean feedBookmarkExists(BookmarkRequestDto requestDto) {
        return feedBookmarkMongoRepository
                .existsByMemberUuidAndFeedUuid(requestDto.getMemberUuid(), requestDto.getBookmarkUuid());

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
    public CursorPage<String> getShortsBookmarks(String memberUuid, String lastId, int pageSize, int pageNo) {
        CursorPage<ShortsBookmarkEntity> cursorPage = shortsBookmarkMongoRepositoryCustom.getShortsBookmarks(
                memberUuid, lastId, pageSize, pageNo);

        List<String> shortsUuids = cursorPage.getContent().stream()
                .map(ShortsBookmarkEntity::getShortsUuid)
                .toList();

        return CursorPage.toCursorPage(cursorPage, shortsUuids);
    }

    @Override
    public boolean shortsBookmarkExists(BookmarkRequestDto requestDto) {
        return shortsBookmarkMongoRepository
                .existsByMemberUuidAndShortsUuid(requestDto.getMemberUuid(), requestDto.getBookmarkUuid());
    }

}
