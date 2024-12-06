package com.mulmeong.notification.application;

import com.mulmeong.notification.document.NotificationHistory;
import com.mulmeong.notification.document.NotificationStatus;
import com.mulmeong.notification.document.NotificationType;
import com.mulmeong.notification.dto.NoticeResponseDto;
import com.mulmeong.notification.infrastructure.EmitterRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.web.servlet.mvc.method.annotation.SseEmitter;

import java.io.IOException;
import java.util.Map;

import static org.springframework.retry.policy.TimeoutRetryPolicy.DEFAULT_TIMEOUT;

@Slf4j
@RequiredArgsConstructor
@Service
public class SseServiceImpl implements SseService {

    private final EmitterRepository emitterRepository;

    @Override
    public SseEmitter subscribe(String memberUuid, String lastEventId) {
        String emitterId = makeTimeIncludeId(memberUuid); //사용자의 실시간 연결 식별
        SseEmitter emitter = emitterRepository.save(emitterId, new SseEmitter(DEFAULT_TIMEOUT));
        emitter.onCompletion(() -> emitterRepository.deleteById(emitterId)); //sse 연결이 정상적으로 완료되었을 때
        emitter.onTimeout(() -> emitterRepository.deleteById(emitterId)); //sse  연결이 타임아웃되었을 때
        String eventId = makeTimeIncludeId(memberUuid); //전송하는 각 이벤트를 식별하는 id
        sendNotificationToClient(emitter, eventId,
                emitterId, "알림 서버 연결 성공. [memberUuid = " + memberUuid + "]"); //503 에러 방지 더미데이터 전송
        if (!lastEventId.isEmpty()) { //미수신 데이터 전송
            sendLostData(lastEventId, memberUuid, emitterId, emitter);
        }
        return emitter;
    }

    private String makeTimeIncludeId(String uuid) {
        return uuid + "_" + System.currentTimeMillis();
    }

    @Override
    public void sendNotificationToClient(SseEmitter emitter, String eventId, String emitterId, Object data) {
        log.info("data 알림: {}", eventId);
        log.info("data: {}", data);
        try {
            emitter.send(SseEmitter.event()
                    .id(eventId)
                    .data(data));
        } catch (IOException e) {
            emitterRepository.deleteById(emitterId);
        }
    }

    @Override
    public void send(NotificationHistory notificationHistory) {
        String receiverUuid = notificationHistory.getMemberUuid(); //알림 대상자
        String eventId = makeTimeIncludeId(receiverUuid); //이벤트 생성 시간
        //수산자의 모든 SseEmitter 객체 가져오기
        Map<String, SseEmitter> emitters = emitterRepository.findAllEmitterByUserUuid(receiverUuid);
        emitters.forEach(//알림 전송
                (key, emitter) -> {
                    emitterRepository.saveEventCache(key, emitter); //알림 전송 기록 저장
                    sendNotificationToClient(emitter, eventId, key, NoticeResponseDto.toDto(notificationHistory));
                }
        );
    }

    private void sendLostData(String lastEventId, String memberUuid, String emitterId, SseEmitter emitter) {
        Map<String, Object> eventCaches = emitterRepository
                .findAllEventCacheByUserUuid(String.valueOf(memberUuid)); //사용자의 모든 캐시 알림 데이터
        eventCaches.entrySet().stream()
                .filter(entry -> lastEventId.compareTo(entry.getKey()) < 0)
                .forEach(entry -> sendNotificationToClient(emitter, entry.getKey(), emitterId, entry.getValue()));
    }

}
