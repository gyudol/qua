package com.mulmeong.notification.presentation;

import com.mulmeong.notification.application.SseService;
import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.method.annotation.SseEmitter;

@RestController
@RequestMapping("auth/v1")
@RequiredArgsConstructor
public class SseController {

    private final SseService sseService;

    @GetMapping(value = "/subscribe", produces = "text/event-stream")
    @Operation(summary = "sse 구독하기", tags = {"Notification Service"})
    public SseEmitter subscribe(@RequestParam String memberUuid,
                                @RequestHeader(value = "Last-Event-ID", required = false, defaultValue = "")
                                String lastEventId) {
        //Last-Event-ID: 클라이언트가 마지막으로 수신한 데이터의 id 값 -> 유실된 데이터 보내줄 수 있음
        return sseService.subscribe(memberUuid, lastEventId);
    }
}
