package com.mulmeong.notification.infrastructure;

import com.mulmeong.notification.document.NotificationStatus;
import com.mulmeong.notification.document.NotificationType;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface NotificationStatusRepository extends MongoRepository<NotificationStatus, String> {
    Optional<NotificationStatus> findByMemberUuidAndNotificationType(
            String memberUuid, NotificationType notificationType);

    List<NotificationStatus> findAllByMemberUuid(String memberUuid);

    boolean existsByMemberUuid(String memberUuid);
}
