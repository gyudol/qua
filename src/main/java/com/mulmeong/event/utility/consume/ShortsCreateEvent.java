package com.mulmeong.event.utility.consume;

import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Data
@Getter
@NoArgsConstructor
public class ShortsCreateEvent {

    private String shortsUuid;
    private String memberUuid;
    private String title;

}
