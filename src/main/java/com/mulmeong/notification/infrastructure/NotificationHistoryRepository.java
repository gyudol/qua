package com.mulmeong.notification.infrastructure;

import com.mulmeong.notification.document.NotificationHistory;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface NotificationHistoryRepository extends MongoRepository<NotificationHistory, String> {
    Optional<NotificationHistory> findByNotificationHistoryUuid(String notificationHistoryUuid);
}