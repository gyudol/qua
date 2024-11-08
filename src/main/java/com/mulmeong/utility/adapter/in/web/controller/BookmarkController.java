package com.mulmeong.utility.adapter.in.web.controller;

import com.mulmeong.utility.adapter.in.web.vo.FeedBookmarkRequestVo;
import com.mulmeong.utility.adapter.in.web.vo.ShortsBookmarkRequestVo;
import com.mulmeong.utility.application.port.in.BookmarkUseCase;
import com.mulmeong.utility.application.port.in.dto.BookmarkRequestDto;
import com.mulmeong.utility.common.response.BaseResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RequiredArgsConstructor
@RequestMapping("/v1/members")
@RestController
public class BookmarkController {

    private final BookmarkUseCase bookmarkUseCase;

    @PostMapping("/{memberUuid}/feeds/bookmarks")
    public BaseResponse<Void> addFeedBookmark(@PathVariable("memberUuid") String memberUuid, @RequestBody FeedBookmarkRequestVo requestVo) {

        bookmarkUseCase.addFeedBookmark(requestVo.toDto(memberUuid));
        return new BaseResponse<>();
    }

    @DeleteMapping("/{memberUuid}/feeds/{feedUuid}/bookmarks")
    public BaseResponse<Void> deleteFeedBookmark(@PathVariable("memberUuid") String memberUuid, @PathVariable("feedUuid") String feedUuid) {
        bookmarkUseCase.deleteFeedBookmark(new BookmarkRequestDto(memberUuid, feedUuid));
        return new BaseResponse<>();
    }

    @GetMapping("/{memberUuid}/feeds/bookmarks")
    public BaseResponse<List<String>> getFeedBookmarks (@PathVariable("memberUuid") String memberUuid) {
        return new BaseResponse<>(bookmarkUseCase.getFeedBookmarks(memberUuid));
    }

    @PostMapping("/{memberUuid}/shorts/bookmarks")
    public BaseResponse<Void> addShortsBookmark(@PathVariable("memberUuid") String memberUuid, @RequestBody ShortsBookmarkRequestVo requestVo) {

        bookmarkUseCase.addShortsBookmark(requestVo.toDto(memberUuid));
        return new BaseResponse<>();
    }

    @DeleteMapping("/{memberUuid}/shorts/{shortsUuid}/bookmarks")
    public BaseResponse<Void> deleteShortsBookmark(@PathVariable("memberUuid") String memberUuid, @PathVariable("shortsUuid") String shortsUuid) {
        bookmarkUseCase.deleteShortsBookmark(new BookmarkRequestDto(memberUuid, shortsUuid));
        return new BaseResponse<>();
    }

    @GetMapping("/{memberUuid}/shorts/bookmarks")
    public BaseResponse<List<String>> getShortsBookmarks (@PathVariable("memberUuid") String memberUuid) {
        return new BaseResponse<>(bookmarkUseCase.getShortsBookmarks(memberUuid));
    }
}
