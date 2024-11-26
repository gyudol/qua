package com.mulmeong.event.comment;

import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Data
@Getter
@NoArgsConstructor
public class ShortsRecommentDeleteEvent {

    private String recommentUuid;

}
