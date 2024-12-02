package com.mulmeong.event.contest.consume;


import com.mulmeong.contest.read.entity.ContestPost;
import com.mulmeong.contest.read.entity.MediaType;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@Getter
@NoArgsConstructor
public class ContestPostCreateEvent {

    private String postUuid;
    private Long contestId;
    private String memberUuid;
    private String mediaUrl;
    private MediaType mediaType;
    private LocalDateTime createdAt;


    public ContestPost toEntity() {
        return ContestPost.builder()
                .postUuid(postUuid)
                .contestId(contestId)
                .memberUuid(memberUuid)
                .mediaUrl(mediaUrl)
                .mediaType(mediaType)
                .createdAt(createdAt)
                .voteCount(0)
                .build();
    }
}
