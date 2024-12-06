package com.mulmeong.notification.infrastructure;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.mvc.method.annotation.SseEmitter;

import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.stream.Collectors;

@Component
@RequiredArgsConstructor
public class EmitterRepositoryImpl implements EmitterRepository {

    private final Map<String, SseEmitter> emitters = new ConcurrentHashMap<>();
    private final Map<String, Object> eventCache = new ConcurrentHashMap<>();

    @Override
    public SseEmitter save(String emitterId, SseEmitter sseEmitter) {
        emitters.put(emitterId, sseEmitter);
        return sseEmitter;
    }

    @Override
    public void saveEventCache(String eventCacheId, Object event) {
        eventCache.put(eventCacheId, event);
    }

    @Override
    public Map<String, SseEmitter> findAllEmitterByUserUuid(String userUuid) {
        return emitters.entrySet().stream()
                .filter(entry -> entry.getKey().startsWith(userUuid))
                .collect(Collectors.toMap(
                        Map.Entry::getKey,
                        Map.Entry::getValue));

    }

    @Override
    public Map<String, Object> findAllEventCacheByUserUuid(String userUuid) {
        return eventCache.entrySet().stream()
                .filter(entry -> entry.getKey().startsWith(userUuid))
                .collect(Collectors.toMap(
                        Map.Entry::getKey,
                        Map.Entry::getValue));
    }

    @Override
    public void deleteById(String emitterId) {
        emitters.remove(emitterId);
    }

    @Override
    public void deleteAllEmitterByUserUuid(String userUuid) {
        List<String> emitterIds = emitters.keySet().stream()
                .filter(key -> key.startsWith(userUuid))
                .toList();

        emitterIds.forEach(emitters::remove);
    }

    @Override
    public void deleteAllEventCacheByUserUuid(String userUuid) {
        List<String> eventIds = eventCache.keySet().stream()
                .filter(key -> key.startsWith(userUuid))
                .toList();

        eventIds.forEach(eventCache::remove);
    }

}
