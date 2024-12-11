package com.mulmeong.notification.client.comment;

import lombok.Builder;
import lombok.Data;
import lombok.Getter;

@Getter
@Builder
@Data
public class ShortsCommentDto {
    private String shortsUuid;
    private String memberUuid;
    private String commentUuid;
}
