package com.mulmeong.utility.adapter.in.web.controller;

import com.mulmeong.utility.adapter.in.web.vo.FeedBookmarkRequestVo;
import com.mulmeong.utility.application.port.in.BookmarkUseCase;
import com.mulmeong.utility.common.response.BaseResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RequiredArgsConstructor
@RequestMapping("/v1/members")
@RestController
public class BookmarkController {

    private final BookmarkUseCase bookmarkUseCase;

    @PostMapping("/{memberUuid}/feeds/bookmarks")
    public BaseResponse<Void> addBookmark(@PathVariable("memberUuid") String memberUuid, @RequestBody FeedBookmarkRequestVo requestVo) {

        bookmarkUseCase.addFeedBookmark(requestVo.toDto(memberUuid));
        return new BaseResponse<>();
    }

}
