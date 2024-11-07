package com.mulmeong.utility.adapter.out.infrastructure.mongo.repository.bookmarkRepository;

import com.mulmeong.utility.adapter.out.infrastructure.mongo.entity.FeedBookmarkEntity;
import com.mulmeong.utility.adapter.out.infrastructure.mongo.mapper.BookmarkEntityMapper;
import com.mulmeong.utility.application.port.in.dto.BookmarkRequestDto;
import com.mulmeong.utility.application.port.out.BookmarkPort;
import com.mulmeong.utility.application.port.out.dto.BookmarkResponseDto;
import com.mulmeong.utility.common.exception.BaseException;
import com.mulmeong.utility.common.response.BaseResponseStatus;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
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

    @Override
    public void addFeedBookmark(BookmarkResponseDto responseDto) {
        if (feedBookmarkMongoRepository.existsByMemberUuidAndFeedUuid(responseDto.getMemberUuid(), responseDto.getBookmarkUuid())) {
            throw new BaseException(BaseResponseStatus.DUPLICATED_BOOKMARK);
        }
        feedBookmarkMongoRepository.save(bookmarkEntityMapper.toFeedBookmarkEntity(responseDto));
    }

    @Override
    public void deleteFeedBookmark(BookmarkRequestDto bookmarkRequestDto) {
        feedBookmarkMongoRepository.deleteByMemberUuidAndFeedUuid(bookmarkRequestDto.getMemberUuid(), bookmarkRequestDto.getBookmarkUuid());
    }

    @Override
    public List<BookmarkResponseDto> getFeedBookmarks(String memberUuid) {
        List<FeedBookmarkEntity> entities = feedBookmarkMongoRepository.findAllByMemberUuid(memberUuid);
        return entities.stream()
                .map(bookmarkEntityMapper::toDto)
                .collect(Collectors.toList());
    }

    @Override
    public void addShortsBookmark(BookmarkResponseDto responseDto) {
        if (shortsBookmarkMongoRepository.existsByMemberUuidAndShortsUuid(responseDto.getMemberUuid(), responseDto.getBookmarkUuid())) {
            throw new BaseException(BaseResponseStatus.DUPLICATED_BOOKMARK);
        }
        shortsBookmarkMongoRepository.save(bookmarkEntityMapper.toShortsBookmarkEntity(responseDto));
    }

    @Override
    public void deleteShortsBookmark(BookmarkRequestDto bookmarkRequestDto) {
        shortsBookmarkMongoRepository.deleteByMemberUuidAndShortsUuid(bookmarkRequestDto.getMemberUuid(), bookmarkRequestDto.getBookmarkUuid());
    }
}
