package com.mulmeong.utility.adapter.in.web.controller;

import com.mulmeong.utility.adapter.in.web.vo.ReactionRequestVo;
import com.mulmeong.utility.application.port.in.ReactionUseCase;
import com.mulmeong.utility.common.response.BaseResponse;
import com.mulmeong.utility.common.response.BaseResponseStatus;
import com.mulmeong.utility.domain.model.Reaction;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;

@Slf4j
@RequiredArgsConstructor
@RequestMapping("/v1/members")
@RestController
public class ReactionController {

    private final ReactionUseCase reactionUseCase;


    @PostMapping("/likes")
    public Mono<BaseResponse<Reaction>> likes(@RequestBody ReactionRequestVo request) {
        return reactionUseCase.likes(request.toDto())
                .map(reaction -> new BaseResponse<>(BaseResponseStatus.SUCCESS, reaction)) // 성공 시 Response 반환
                .onErrorResume(e -> {
                    // 예외 처리
                    return Mono.just(new BaseResponse<>(BaseResponseStatus.INTERNAL_SERVER_ERROR));
                });
    }


}
