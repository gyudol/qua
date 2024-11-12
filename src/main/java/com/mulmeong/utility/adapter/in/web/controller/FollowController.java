package com.mulmeong.utility.adapter.in.web.controller;

import com.mulmeong.utility.application.port.in.FollowUseCase;
import com.mulmeong.utility.application.port.in.dto.FollowRequestDto;
import com.mulmeong.utility.common.response.BaseResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RequiredArgsConstructor
@RequestMapping("/v1/members")
@RestController
public class FollowController {

    private final FollowUseCase followUseCase;

    @PostMapping("/{sourceUuid}/following/{targetUuid}")
    public BaseResponse<Void> follow(@PathVariable String sourceUuid, @PathVariable String targetUuid) {
        followUseCase.follow(new FollowRequestDto(sourceUuid, targetUuid));
        return new BaseResponse<>();
    }

    @DeleteMapping("/{sourceUuid}/following/{targetUuid}")
    public BaseResponse<Void> unfollow(@PathVariable String sourceUuid, @PathVariable String targetUuid) {
        followUseCase.unfollow(new FollowRequestDto(sourceUuid, targetUuid));
        return new BaseResponse<>();
    }

    @GetMapping("/{sourceUuid}/follow-status/{targetUuid}")
    public BaseResponse<Boolean> getFollowStatus(@PathVariable String sourceUuid, @PathVariable String targetUuid) {
        return new BaseResponse<>(followUseCase.followStatus(new FollowRequestDto(sourceUuid, targetUuid)));
    }



}
