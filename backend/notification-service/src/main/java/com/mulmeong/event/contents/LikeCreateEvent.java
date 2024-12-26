package com.mulmeong.event.contents;

import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Data
@Getter
@NoArgsConstructor
public class LikeCreateEvent {
    private String memberUuid;
    private String kind;
    private String kindUuid;
}