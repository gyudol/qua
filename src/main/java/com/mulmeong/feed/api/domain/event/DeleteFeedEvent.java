package com.mulmeong.feed.api.domain.event;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public class DeleteFeedEvent {

    private String feedUuid;

}
