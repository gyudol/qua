package com.mulmeong.event;

import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Data
@Getter
@NoArgsConstructor
public class FeedRecommentDeleteEvent {

    private String recommentUuid;

    public FeedRecommentDeleteEvent(FeedRecommentDeleteEvent dto) {
        this.recommentUuid = dto.getRecommentUuid();
    }
}
