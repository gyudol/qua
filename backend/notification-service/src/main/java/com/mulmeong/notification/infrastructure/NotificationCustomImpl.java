package com.mulmeong.notification.infrastructure;

import com.mulmeong.notification.common.utils.CursorPage;
import com.mulmeong.notification.document.*;
import com.querydsl.core.BooleanBuilder;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.repository.support.SpringDataMongodbQuery;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
@RequiredArgsConstructor
@Slf4j
public class NotificationCustomImpl implements NotificationCustom {
    private static final int DEFAULT_PAGE_SIZE = 10;
    private static final int DEFAULT_PAGE_NUMBER = 0;
    private final MongoTemplate mongoTemplate;

    @Override
    public CursorPage<NotificationHistory> getNotificationHistories(
            String memberUuid,
            String type,
            String kind,
            String lastId,
            Integer pageSize,
            Integer pageNo) {

        QNotificationHistory notificationHistory = QNotificationHistory.notificationHistory;

        BooleanBuilder builder = new BooleanBuilder();

        builder.and(notificationHistory.targetUuid.eq(memberUuid));

        Optional.ofNullable(lastId)
                .ifPresent(id -> builder.and(notificationHistory.id.lt(id)));

        if (type.equals("unread")) {
            builder.and(notificationHistory.isRead.eq(false));
        }

        if (kind != null) {
            builder.and(notificationHistory.notificationType.eq(NotificationType.valueOf(kind.toUpperCase())));
        }

        int currentPage = pageNo != null ? pageNo : DEFAULT_PAGE_NUMBER;
        int currentPageSize = pageSize != null ? pageSize : DEFAULT_PAGE_SIZE;
        int offset = Math.max(0, (currentPage - 1) * currentPageSize);

        SpringDataMongodbQuery<NotificationHistory> query = new SpringDataMongodbQuery<>(
                mongoTemplate, NotificationHistory.class);

        List<NotificationHistory> notificationHistories = query.where(builder)
                .orderBy(notificationHistory.id.desc())
                .offset(offset)
                .limit(currentPageSize + 1)
                .fetch();

        boolean hasNext = notificationHistories.size() > currentPageSize;
        String nextCursor = null;
        if (hasNext) {
            notificationHistories = notificationHistories.subList(0, currentPageSize);
            nextCursor = notificationHistories.get(currentPageSize - 1).getId();
        }

        return new CursorPage<>(notificationHistories, nextCursor, hasNext, pageSize, pageNo);
    }

}
