package com.mulmeong.utility.adapter.in.web.controller;

import com.mulmeong.utility.adapter.in.web.vo.FeedBookmarkRequestVo;
import com.mulmeong.utility.adapter.in.web.vo.ShortsBookmarkRequestVo;
import com.mulmeong.utility.application.port.in.BookmarkUseCase;
import com.mulmeong.utility.application.port.in.dto.BookmarkRequestDto;
import com.mulmeong.utility.application.port.out.dto.FeedBookmarkResponseDto;
import com.mulmeong.utility.common.response.BaseResponse;
import com.mulmeong.utility.common.utils.CursorPage;
import io.swagger.v3.oas.annotations.Operation;
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

    @Operation(summary = "피드 북마크 추가", tags = {"Bookmark Service"})
    @PostMapping("/feeds/bookmarks")
    public BaseResponse<Void> addFeedBookmark(
            @RequestHeader("Member-Uuid") String memberUuid, @RequestBody FeedBookmarkRequestVo requestVo) {

        bookmarkUseCase.addFeedBookmark(requestVo.toDto(memberUuid));
        return new BaseResponse<>();
    }

    @Operation(summary = "피드 북마크 삭제", tags = {"Bookmark Service"})
    @DeleteMapping("/feeds/{feedUuid}/bookmarks")
    public BaseResponse<Void> deleteFeedBookmark(
            @RequestHeader("Member-Uuid") String memberUuid, @PathVariable("feedUuid") String feedUuid) {
        bookmarkUseCase.deleteFeedBookmark(new BookmarkRequestDto(memberUuid, feedUuid));
        return new BaseResponse<>();
    }

    @Operation(summary = "피드 북마크 목록 조회", tags = {"Bookmark Service"})
    @GetMapping("/feeds/bookmarks")
    public BaseResponse<CursorPage<String>> getFeedBookmarks(
            @RequestHeader("Member-Uuid") String memberUuid,
            @RequestParam(value = "nextCursor", required = false) String lastId,
            @RequestParam(value = "pageSize", defaultValue = "10") int pageSize,
            @RequestParam(value = "pageNo", defaultValue = "0") int pageNo) {

        return new BaseResponse<>(bookmarkUseCase.getFeedBookmarks(memberUuid, lastId, pageSize, pageNo));
    }

    @Operation(summary = "쇼츠 북마크 추가", tags = {"Bookmark Service"})
    @PostMapping("/shorts/bookmarks")
    public BaseResponse<Void> addShortsBookmark(
            @RequestHeader("Member-Uuid") String memberUuid, @RequestBody ShortsBookmarkRequestVo requestVo) {

        bookmarkUseCase.addShortsBookmark(requestVo.toDto(memberUuid));
        return new BaseResponse<>();
    }

    @Operation(summary = "쇼츠 북마크 삭제", tags = {"Bookmark Service"})
    @DeleteMapping("/shorts/{shortsUuid}/bookmarks")
    public BaseResponse<Void> deleteShortsBookmark(
            @RequestHeader("Member-Uuid") String memberUuid, @PathVariable("shortsUuid") String shortsUuid) {
        bookmarkUseCase.deleteShortsBookmark(new BookmarkRequestDto(memberUuid, shortsUuid));
        return new BaseResponse<>();
    }

    @Operation(summary = "쇼츠 북마크 목록 조회", tags = {"Bookmark Service"})
    @GetMapping("/shorts/bookmarks")
    public BaseResponse<CursorPage<String>> getShortsBookmarks(
            @RequestHeader("Member-Uuid") String memberUuid,
            @RequestParam(value = "nextCursor", required = false) String lastId,
            @RequestParam(value = "pageSize", defaultValue = "10") int pageSize,
            @RequestParam(value = "pageNo", defaultValue = "0") int pageNo) {

        return new BaseResponse<>(bookmarkUseCase.getShortsBookmarks(memberUuid, lastId, pageSize, pageNo));
    }
}
