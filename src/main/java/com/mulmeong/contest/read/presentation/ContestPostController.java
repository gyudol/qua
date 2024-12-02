package com.mulmeong.contest.read.presentation;

import com.mulmeong.contest.read.application.ContestPostService;
import com.mulmeong.contest.read.common.exception.BaseException;
import com.mulmeong.contest.read.common.response.BaseResponse;
import com.mulmeong.contest.read.common.utils.CursorPage;
import com.mulmeong.contest.read.dto.in.ContestPostRequestDto;
import com.mulmeong.contest.read.dto.out.ContestPostResponseDto;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@RequestMapping("/auth/v1/contests")
@RestController
public class ContestPostController {

    private final ContestPostService contestPostService;

    @GetMapping("/{contestId}")
    public BaseResponse<CursorPage<ContestPostResponseDto>> getContestPosts(
            @PathVariable("contestId") Long contestId,
            @RequestParam(defaultValue = "latest", required = false) String sortBy,
            @RequestParam(required = false, name = "nextCursor") String lastId,
            @RequestParam(required = false) Integer pageSize,
            @RequestParam(required = false) Integer pageNo
    ) {
        return new BaseResponse<>(contestPostService.getPosts(
                ContestPostRequestDto.toDto(contestId, sortBy, lastId, pageSize, pageNo)));
    }

    @GetMapping("/posts/{postUuid}")
    public BaseResponse<ContestPostResponseDto> getContestPosts(
            @PathVariable("postUuid") String postUuid
    ) {
        return new BaseResponse<>(contestPostService.getContestPost(postUuid));
    }
}
