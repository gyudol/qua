package com.mulmeong.utility.adapter.in.web.controller;

import com.mulmeong.utility.adapter.in.web.vo.LikesRequestVo;
import com.mulmeong.utility.application.port.in.LikesUseCase;
import com.mulmeong.utility.application.port.in.dto.LikesRequestDto;
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
public class AuthLikesController {

    private final LikesUseCase likesUseCase;

    @Operation(summary = "좋아요 추가", tags = {"like Service"})
    @PostMapping("/likes")
    public BaseResponse<Void> likes(@RequestBody LikesRequestVo request) {

        likesUseCase.likes(request.toDto());
        return new BaseResponse<>();

    }

    @Operation(summary = "좋아요 상태 조회", tags = {"like Service"})
    @GetMapping("/{kind}/{kindUuid}/like-status")
    public BaseResponse<Boolean> likesStatus(
            @RequestHeader("Member-Uuid") String memberUuid, @PathVariable String kind, @PathVariable String kindUuid) {

        return new BaseResponse<>(likesUseCase.isChecked(new LikesRequestDto(memberUuid, kind, kindUuid)));

    }

    @Operation(summary = "좋아요 한 컨텐츠 조회", tags = {"like Service"})
    @GetMapping("/{kind}/likes")
    public BaseResponse<CursorPage<String>> getLikes(
            @RequestHeader("Member-Uuid") String memberUuid,
            @PathVariable("kind") String kind,
            @RequestParam(value = "nextCursor", required = false) String lastId,
            @RequestParam(value = "pageSize", defaultValue = "10") int pageSize,
            @RequestParam(value = "pageNo", defaultValue = "0") int pageNo) {

        return new BaseResponse<>(likesUseCase.getLikes(memberUuid, kind, lastId, pageSize, pageNo));
    }


}
