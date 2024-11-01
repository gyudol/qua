package com.mulmeong.utility.domain.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;


@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Document(collation = "reaction")
public class Reaction {

    @Id
    private String id;
    private String memberUuid;
    private String kind;
    // 해당 kind(feed, shorts,comment, re-comment)의 uuid
    private String kindUuid;
    private Integer status;

}
