package com.mulmeong.event.member;

import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Data
@Getter
@NoArgsConstructor
public class FollowCreateEvent {
    private String sourceUuid;
    private String targetUuid;
}