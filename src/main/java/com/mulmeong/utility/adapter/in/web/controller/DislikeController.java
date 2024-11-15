package com.mulmeong.utility.adapter.in.web.controller;

import com.mulmeong.utility.adapter.in.web.vo.DislikeRequestVo;
import com.mulmeong.utility.application.port.in.DislikeUseCase;
import com.mulmeong.utility.application.port.in.dto.DislikeListRequestDto;
import com.mulmeong.utility.application.port.in.dto.DislikeRequestDto;
import com.mulmeong.utility.common.response.BaseResponse;
import com.mulmeong.utility.common.response.BaseResponseStatus;
import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RequiredArgsConstructor
@RequestMapping("auth/v1/members")
@RestController
public class DislikeController {

    private final DislikeUseCase dislikeUseCase;

    @Operation(summary = "싫어요 추가", tags = {"like Service"})
    @PostMapping("/dislike")
    public BaseResponse<Void> dislike(@RequestBody DislikeRequestVo request) {

        dislikeUseCase.dislike(request.toDto());
        return new BaseResponse<>();


    }

    @Operation(summary = "싫어요 상태 조회", tags = {"like Service"})
    @GetMapping("/{kind}/{kindUuid}/dislike-status")
    public BaseResponse<Boolean> dislikeStatus(
            @RequestHeader("Member-Uuid") String memberUuid, @PathVariable String kind, @PathVariable String kindUuid) {

        return new BaseResponse<>(dislikeUseCase.isChecked(new DislikeRequestDto(memberUuid, kind, kindUuid)));

    }

}
