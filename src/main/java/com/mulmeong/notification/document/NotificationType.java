package com.mulmeong.notification.document;

import lombok.*;

@Getter
@RequiredArgsConstructor
public enum NotificationType {
    FEED("feed"),
    SHORTS("shorts"),
    COMMENT("comment"),
    RECOMMENT("recomment"),
    LIKE("like"),
    FOLLOW("follow"),
    CHAT("chat"),
    CONTEST("contest"),
    GRADE("grade"),
    REPORT("report");

    private final String kind;
}
