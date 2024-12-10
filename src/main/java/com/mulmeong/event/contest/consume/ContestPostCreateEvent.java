package com.mulmeong.event.contest.consume;


import com.mulmeong.contest.read.domain.document.ContestPost;
import com.mulmeong.contest.read.domain.model.Media;
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
    private Media media;
    private LocalDateTime createdAt;


    public ContestPost toEntity() {
        return ContestPost.builder()
                .postUuid(postUuid)
                .contestId(contestId)
                .memberUuid(memberUuid)
                .media(media)
                .createdAt(createdAt)
                .voteCount(0)
                .build();
    }
}
