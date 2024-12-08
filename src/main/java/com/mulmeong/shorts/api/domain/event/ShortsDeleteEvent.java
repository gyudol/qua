package com.mulmeong.shorts.api.domain.event;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public class ShortsDeleteEvent {

    private String shortsUuid;

}
