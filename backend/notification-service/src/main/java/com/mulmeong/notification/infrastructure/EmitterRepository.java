package com.mulmeong.notification.infrastructure;

import org.springframework.web.servlet.mvc.method.annotation.SseEmitter;

import java.util.Map;

public interface EmitterRepository {
    SseEmitter save(String emitterId, SseEmitter sseEmitter);

    void saveEventCache(String eventCacheId, Object event);

    Map<String, SseEmitter> findAllEmitterByUserUuid(String userUuid);

    Map<String, Object> findAllEventCacheByUserUuid(String userUuid);

    void deleteById(String emitterId);

    void deleteAllEmitterByUserUuid(String userUuid);

    void deleteAllEventCacheByUserUuid(String userUuid);

}
