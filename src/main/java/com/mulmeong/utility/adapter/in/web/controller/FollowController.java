package com.mulmeong.utility.adapter.in.web.controller;

import com.mulmeong.utility.application.port.in.FollowUseCase;
import com.mulmeong.utility.application.port.in.dto.FollowRequestDto;
import com.mulmeong.utility.common.response.BaseResponse;
import com.mulmeong.utility.common.utils.CursorPage;
import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RequiredArgsConstructor
@RequestMapping("/v1/members")
@RestController
public class FollowController {

    private final FollowUseCase followUseCase;

    @Operation(summary = "해당 member의 팔로워 조회", tags = {"follow Service"})
    @GetMapping("/{memberUuid}/followers")
    public BaseResponse<CursorPage<String>> getFollowers(
            @PathVariable String memberUuid,
            @RequestParam(value = "nextCursor", required = false) String lastId,
            @RequestParam(value = "pageSize", defaultValue = "10") int pageSize,
            @RequestParam(value = "pageNo", defaultValue = "0") int pageNo) {
        return new BaseResponse<>(followUseCase.getFollowers(memberUuid, lastId, pageSize, pageNo));
    }

    @Operation(summary = "해당 member의 팔로잉 조회", tags = {"follow Service"})
    @GetMapping("/{memberUuid}/followings")
    public BaseResponse<CursorPage<String>> getFollowings(
            @PathVariable String memberUuid,
            @RequestParam(value = "nextCursor", required = false) String lastId,
            @RequestParam(value = "pageSize", defaultValue = "10") int pageSize,
            @RequestParam(value = "pageNo", defaultValue = "0") int pageNo) {
        return new BaseResponse<>(followUseCase.getFollowings(memberUuid, lastId, pageSize, pageNo));
    }

}
