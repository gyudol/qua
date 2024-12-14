package com.mulmeong.notification.client.feed;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Builder
@Data
public class FeedDto {
    private String feedUuid;
    private String memberUuid;
    private String title;
    private String content;

}