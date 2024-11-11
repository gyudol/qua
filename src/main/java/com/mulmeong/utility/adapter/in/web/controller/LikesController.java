package com.mulmeong.utility.adapter.in.web.controller;

import com.mulmeong.utility.adapter.in.web.vo.LikesRequestVo;
import com.mulmeong.utility.application.port.in.LikesUseCase;
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
public class LikesController {

    private final LikesUseCase likesUseCase;


    @PostMapping("/likes")
    public BaseResponse<Void> likes(@RequestBody LikesRequestVo request) {

        likesUseCase.likes(request.toDto());
        return new BaseResponse<>();

    }


    @GetMapping("/{memberUuid}/{kind}/{kindUuid}/like-status")
    public BaseResponse<Boolean> likesStatus(@PathVariable String memberUuid, @PathVariable String kind, @PathVariable String kindUuid) {

        return new BaseResponse<>(likesUseCase.isChecked(new LikesRequestDto(memberUuid, kind, kindUuid)));

    }


}
