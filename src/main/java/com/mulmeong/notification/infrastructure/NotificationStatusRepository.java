package com.mulmeong.notification.infrastructure;

import com.mulmeong.notification.document.NotificationStatus;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface NotificationStatusRepository extends MongoRepository<NotificationStatus, String> {
}
