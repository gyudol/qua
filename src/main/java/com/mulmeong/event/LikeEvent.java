package com.mulmeong.event;

import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Data
@Getter
@NoArgsConstructor
public class LikeEvent {

    private String memberUuid;
    private String kind; //comment or recomment
    private String kindUuid;

    public LikeEvent(LikeEvent message) {
        this.memberUuid = memberUuid;
        this.kind = kind;
        this.kindUuid = kindUuid;
    }


}
