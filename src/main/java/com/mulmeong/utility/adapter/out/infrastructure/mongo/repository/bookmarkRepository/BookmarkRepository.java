package com.mulmeong.utility.adapter.out.infrastructure.mongo.repository.bookmarkRepository;

import com.mulmeong.utility.adapter.out.infrastructure.mongo.mapper.BookmarkEntityMapper;
import com.mulmeong.utility.application.port.out.BookmarkPort;
import com.mulmeong.utility.application.port.out.dto.BookmarkResponseDto;
import com.mulmeong.utility.common.exception.BaseException;
import com.mulmeong.utility.common.response.BaseResponseStatus;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Repository;

@RequiredArgsConstructor
@Repository
@Slf4j
public class BookmarkRepository implements BookmarkPort {

    private final FeedBookmarkMongoRepository feedBookmarkMongoRepository;
    private final BookmarkEntityMapper bookmarkEntityMapper;

    @Override
    public void addFeedBookmark(BookmarkResponseDto responseDto) {
        if (feedBookmarkMongoRepository.existsByMemberUuidAndFeedUuid(responseDto.getMemberUuid(), responseDto.getBookmarkUuid())) {
            throw new BaseException(BaseResponseStatus.DUPLICATED_BOOKMARK);
        }
        feedBookmarkMongoRepository.save(bookmarkEntityMapper.toEntity(responseDto));
    }
}
