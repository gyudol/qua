package com.mulmeong.notification.client.comment;

import lombok.Builder;
import lombok.Data;
import lombok.Getter;

@Getter
@Builder
@Data
public class FeedRecommentDto {
    private String commentUuid;
    private String memberUuid;
    private String recommentUuid;
}
