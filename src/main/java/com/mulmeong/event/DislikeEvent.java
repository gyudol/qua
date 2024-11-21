package com.mulmeong.event;

import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Data
@Getter
@NoArgsConstructor
public class DislikeEvent {

    private String memberUuid;
    private String kind; //comment or recomment
    private String kindUuid;

    public DislikeEvent(DislikeEvent message) {
        this.memberUuid = memberUuid;
        this.kind = kind;
        this.kindUuid = kindUuid;
    }
}
