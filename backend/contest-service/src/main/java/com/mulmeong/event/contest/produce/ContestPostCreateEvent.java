package com.mulmeong.event.contest.produce;

import com.mulmeong.contest.domain.model.Media;
import lombok.Builder;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
@Builder
public class ContestPostCreateEvent {

    private String postUuid;
    private Long contestId;
    private String memberUuid;
    private Media media;
    private LocalDateTime createdAt;

}
