package com.mulmeong.batchserver.utility.domain.model;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;


@Getter
@NoArgsConstructor
@ToString
public class Dislike {

    private String id;
    private String memberUuid;
    private String kind;
    // 해당 kind(feed, shorts,comment, re-comment)의 uuid
    private String kindUuid;
    private boolean status;

    @Builder
    public Dislike(String id,
                   String memberUuid,
                   String kind,
                   String kindUuid,
                   boolean status) {
        this.id = id;
        this.memberUuid = memberUuid;
        this.kind = kind;
        this.kindUuid = kindUuid;
        this.status = status;
    }

    public void toggleStatus() {
        this.status = !this.status;
    }

}
