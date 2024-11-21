package com.mulmeong.event;

import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Data
@Getter
@NoArgsConstructor
public class ShortsRecommentDeleteEvent {

    private String recommentUuid;

    public ShortsRecommentDeleteEvent(ShortsRecommentDeleteEvent dto) {
        this.recommentUuid = dto.getRecommentUuid();
    }
}
