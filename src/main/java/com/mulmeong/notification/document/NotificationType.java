package com.mulmeong.notification.document;

import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Document(collection = "notification_type")
public class NotificationType {
    @Id
    private String id;
    @Indexed(unique = true)
    private String kind;
}
