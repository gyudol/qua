package com.mulmeong.utility.adapter.in.web.controller;

import com.mulmeong.utility.application.port.in.ReactionUseCase;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RequiredArgsConstructor
@RequestMapping("/v1/members")
@RestController
public class ReactionController {

    private final ReactionUseCase reactionUseCase;

}
