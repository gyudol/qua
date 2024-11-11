package com.mulmeong.utility.adapter.in.web.controller;

import com.mulmeong.utility.adapter.in.web.vo.DislikeRequestVo;
import com.mulmeong.utility.adapter.in.web.vo.LikesRequestVo;
import com.mulmeong.utility.application.port.in.DislikeUseCase;
import com.mulmeong.utility.application.port.in.LikesUseCase;
import com.mulmeong.utility.application.port.in.dto.DislikeRequestDto;
import com.mulmeong.utility.application.port.in.dto.LikesRequestDto;
import com.mulmeong.utility.common.response.BaseResponse;
import com.mulmeong.utility.common.response.BaseResponseStatus;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RequiredArgsConstructor
@RequestMapping("/v1/members")
@RestController
public class DislikeController {

    private final DislikeUseCase dislikeUseCase;

    @PostMapping("/dislike")
    public BaseResponse<Void> dislike(@RequestBody DislikeRequestVo request) {

        dislikeUseCase.dislike(request.toDto());
        return new BaseResponse<>();


    }

    @GetMapping("/{memberUuid}/{kind}/{kindUuid}/dislike-status")
    public BaseResponse<Boolean> dislikeStatus(@PathVariable String memberUuid, @PathVariable String kind, @PathVariable String kindUuid) {

        return new BaseResponse<>(dislikeUseCase.isChecked(new DislikeRequestDto(memberUuid, kind, kindUuid)));

    }

}
