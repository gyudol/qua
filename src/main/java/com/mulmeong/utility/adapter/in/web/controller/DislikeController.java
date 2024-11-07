package com.mulmeong.utility.adapter.in.web.controller;

import com.mulmeong.utility.adapter.in.web.vo.DislikeRequestVo;
import com.mulmeong.utility.application.port.in.DislikeUseCase;
import com.mulmeong.utility.application.port.in.dto.DislikeListRequestDto;
import com.mulmeong.utility.application.port.in.dto.DislikeRequestDto;
import com.mulmeong.utility.common.response.BaseResponse;
import com.mulmeong.utility.common.response.BaseResponseStatus;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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
            return new BaseResponse<>();
        } catch (Exception e) {
            return new BaseResponse<>(BaseResponseStatus.INTERNAL_SERVER_ERROR);
        }

    }

    @GetMapping("/{memberUuid}/{kind}/{kindUuid}/dislike-status")
    public BaseResponse<Boolean> dislikeStatus(@PathVariable String memberUuid, @PathVariable String kind, @PathVariable String kindUuid) {
        try {
            return new BaseResponse<>(dislikeUseCase.isChecked(new DislikeRequestDto(memberUuid, kind, kindUuid)));
        } catch (Exception e) {
            return new BaseResponse<>(BaseResponseStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/{memberUuid}/{kind}/dislike")
    public BaseResponse<List<String>> getDislikes(@PathVariable String memberUuid, @PathVariable String kind) {
        try {
            return new BaseResponse<>(dislikeUseCase.getDislikes(new DislikeListRequestDto(memberUuid, kind)));
        } catch (Exception e) {
            return new BaseResponse<>(BaseResponseStatus.INTERNAL_SERVER_ERROR);
        }
    }

}
