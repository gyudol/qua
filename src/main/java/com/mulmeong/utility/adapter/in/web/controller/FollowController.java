package com.mulmeong.utility.adapter.in.web.controller;

import com.mulmeong.utility.application.port.in.FollowUseCase;
import com.mulmeong.utility.application.port.in.dto.FollowRequestDto;
import com.mulmeong.utility.common.response.BaseResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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




}
