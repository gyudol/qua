package com.mulmeong.batchserver.utility.domain.document;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;


@Getter
@NoArgsConstructor
@ToString
public class Likes {

    private String id;
    private String memberUuid;
    private String kind;
    // 해당 kind(feed, shorts,comment, re-comment)의 uuid
    private String kindUuid;
    private boolean status;

    @Builder
    public Likes(String id,
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
