package com.mulmeong.notification.infrastructure;

import com.mulmeong.notification.common.utils.CursorPage;
import com.mulmeong.notification.document.NotificationHistory;
import com.mulmeong.notification.document.NotificationStatus;
import org.springframework.stereotype.Repository;

@Repository
public interface NotificationCustom {
    CursorPage<NotificationHistory> getNotificationHistories(
            String memberUuid,
            String type,
            String kind,
            String lastId,
            Integer pageSize,
            Integer pageNo
    );

}
