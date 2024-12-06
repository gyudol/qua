package com.mulmeong.notification.infrastructure;

import com.mulmeong.notification.document.NotificationType;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface NotificationTypeRepository extends MongoRepository<NotificationType, String> {
    Optional<NotificationType> findByKind(String kind);

    boolean existsByKind(String kind);
}
