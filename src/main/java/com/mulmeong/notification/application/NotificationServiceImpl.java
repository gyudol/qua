package com.mulmeong.notification.application;

import com.mulmeong.notification.common.exception.BaseException;
import com.mulmeong.notification.common.response.BaseResponseStatus;
import com.mulmeong.notification.common.utils.CursorPage;
import com.mulmeong.notification.document.NotificationHistory;
import com.mulmeong.notification.document.NotificationStatus;
import com.mulmeong.notification.document.NotificationType;
import com.mulmeong.notification.dto.NotificationHistoryResponseDto;
import com.mulmeong.notification.dto.NotificationStatusResponseDto;
import com.mulmeong.notification.infrastructure.NotificationCustom;
import com.mulmeong.notification.infrastructure.NotificationHistoryRepository;
import com.mulmeong.notification.infrastructure.NotificationStatusRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@RequiredArgsConstructor
@Service
public class NotificationServiceImpl implements NotificationService {

    private final NotificationStatusRepository notificationStatusRepository;
    private final NotificationHistoryRepository notificationHistoryRepository;
    private final NotificationCustom notificationCustom;
    private final MongoTemplate mongoTemplate;

    @Override
    public CursorPage<NotificationHistoryResponseDto> getNotificationHistoryByPage(
            String memberUuid,
            String type,
            String kind,
            String lastId,
            Integer pageSize,
            Integer pageNo) {
        CursorPage<NotificationHistory> cursorPage = notificationCustom.getNotificationHistories(
                memberUuid, type, kind, lastId, pageSize, pageNo);
        return CursorPage.toCursorPage(cursorPage, cursorPage.getContent().stream()
                .map(NotificationHistoryResponseDto::toDto).toList());
    }

    @Override
    public List<NotificationStatusResponseDto> getAllNotificationStatus(String memberUuid) {
        return notificationStatusRepository.findAllByMemberUuid(memberUuid).stream().map(
                NotificationStatusResponseDto::toDto
        ).toList();
    }

    @Override
    public void updateNotificationStatus(String memberUuid, String type) {
        Query query = new Query(Criteria.where("memberUuid").is(memberUuid).and("notificationType")
                .is(NotificationType.valueOf(type.toUpperCase())));
        NotificationStatus notificationStatus = mongoTemplate.findOne(query, NotificationStatus.class);
        if (notificationStatus == null) {
            throw new BaseException(BaseResponseStatus.NO_NOTIFICATION_STATUS);
        }
        boolean newStatus = notificationStatus.isNotificationStatus();
        Update update = new Update();
        update.set("notificationStatus", !newStatus);
        mongoTemplate.updateFirst(query, update, NotificationStatus.class);
    }

    @Override
    public void updateNotificationHistoryRead(String memberUuid, String historyUuid) {
        Query query = new Query(Criteria.where("notificationHistoryUuid").is(historyUuid));
        NotificationHistory notificationHistory = mongoTemplate.findOne(query, NotificationHistory.class);

        if (notificationHistory == null) {
            throw new BaseException(BaseResponseStatus.NO_NOTIFICATION_HISTORY);
        }

        if (!notificationHistory.getTargetUuid().equals(memberUuid)) {
            throw new BaseException(BaseResponseStatus.NO_NOTIFICATION_HISTORY_AUTHORITY);
        }
        boolean read = notificationHistory.isRead();
        if (!read) {
            Update update = new Update();
            update.set("isRead", true);
            mongoTemplate.updateFirst(query, update, NotificationHistory.class);
        }
    }

}
