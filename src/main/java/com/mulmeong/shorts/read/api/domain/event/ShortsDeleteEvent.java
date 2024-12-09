package com.mulmeong.shorts.read.api.domain.event;

import lombok.Getter;
import lombok.ToString;

@Getter
@ToString
public class ShortsDeleteEvent {

    private String shortsUuid;

}
