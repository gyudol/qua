package com.mulmeong.utility.adapter.out.infrastructure.mongo.repository;

import com.mulmeong.utility.adapter.out.infrastructure.mongo.mapper.BookmarkEntityMapper;
import com.mulmeong.utility.application.port.out.BookmarkPort;
import com.mulmeong.utility.application.port.out.dto.BookmarkResponseDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Repository;

@RequiredArgsConstructor
@Repository
@Slf4j
public class BookmarkRepository implements BookmarkPort {

    private final BookmarkMongoRepository bookmarkMongoRepository;
    private final BookmarkEntityMapper bookmarkEntityMapper;

    @Override
    public void addBookmark(BookmarkResponseDto responseDto) {
        bookmarkMongoRepository.save(bookmarkEntityMapper.toEntity(responseDto));
    }
}
