package com.mulmeong.utility.adapter.in.web.controller;

import com.mulmeong.utility.adapter.in.web.vo.DislikeRequestVo;
import com.mulmeong.utility.adapter.in.web.vo.LikesRequestVo;
import com.mulmeong.utility.application.port.in.DislikeUseCase;
import com.mulmeong.utility.application.port.in.LikesUseCase;
import com.mulmeong.utility.common.response.BaseResponse;
import com.mulmeong.utility.common.response.BaseResponseStatus;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RequiredArgsConstructor
@RequestMapping("/v1/members")
@RestController
public class DislikeController {

    private final DislikeUseCase dislikeUseCase;

    @PostMapping("/dislike")
    public BaseResponse<Void> dislike(@RequestBody DislikeRequestVo request) {

        try {
            dislikeUseCase.dislike(request.toDto());
            return new BaseResponse<>(BaseResponseStatus.SUCCESS);
        } catch (Exception e) {
            return new BaseResponse<>(BaseResponseStatus.INTERNAL_SERVER_ERROR);
        }

    }

}
