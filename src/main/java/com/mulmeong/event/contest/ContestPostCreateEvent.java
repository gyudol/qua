package com.mulmeong.event.contest;

import com.mulmeong.contest.entity.ContestPost;
import com.mulmeong.contest.entity.MediaType;
import jakarta.persistence.*;
import lombok.Builder;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
@Builder
public class ContestPostCreateEvent {

    private String postUuid;
    private Long contestId;
    private String memberUuid;
    private String mediaUrl;
    private MediaType mediaType;
    private LocalDateTime createdAt;

    public static ContestPostCreateEvent toDto(ContestPost contestPost) {
        return ContestPostCreateEvent.builder()
                .postUuid(contestPost.getPostUuid())
                .contestId(contestPost.getContestId())
                .memberUuid(contestPost.getMemberUuid())
                .mediaUrl(contestPost.getMediaUrl())
                .mediaType(contestPost.getMediaType())
                .createdAt(contestPost.getCreatedAt())
                .build();
    }

}
