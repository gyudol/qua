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
@RequestMapping("/auth/v1/members")
@RestController
public class AuthFollowController {

    private final FollowUseCase followUseCase;

    @Operation(summary = "source -> target 팔로우", tags = {"follow Service"})
    @PostMapping("/{sourceUuid}/following/{targetUuid}")
    public BaseResponse<Void> follow(@PathVariable String sourceUuid, @PathVariable String targetUuid) {
        FollowRequestDto requestDto = new FollowRequestDto(sourceUuid, targetUuid);
        followUseCase.follow(requestDto);
        return new BaseResponse<>();
    }

    @Operation(summary = "source -> target 팔로우 삭제", tags = {"follow Service"})
    @DeleteMapping("/{sourceUuid}/following/{targetUuid}")
    public BaseResponse<Void> unfollow(@PathVariable String sourceUuid, @PathVariable String targetUuid) {
        followUseCase.unfollow(new FollowRequestDto(sourceUuid, targetUuid));
        return new BaseResponse<>();
    }

    @Operation(summary = "source -> target 팔로우 상태 조회", tags = {"follow Service"})
    @GetMapping("/{sourceUuid}/follow-status/{targetUuid}")
    public BaseResponse<Boolean> getFollowStatus(@PathVariable String sourceUuid, @PathVariable String targetUuid) {
        return new BaseResponse<>(followUseCase.followStatus(new FollowRequestDto(sourceUuid, targetUuid)));
    }

}
