package com.mulmeong.contest.read.dto.out;

import com.mulmeong.contest.read.domain.document.ContestPost;
import com.mulmeong.contest.read.domain.model.Media;
import com.mulmeong.contest.read.domain.model.MediaType;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Getter
@NoArgsConstructor
public class ContestPostResponseDto {

    private String postUuid;
    private Long contestId;
    private String memberUuid;
    private Media media;
    private LocalDateTime createdAt;
    private Integer voteCount;

    @Builder
    public ContestPostResponseDto(
            String postUuid,
            Long contestId,
            String memberUuid,
            Media media,
            LocalDateTime createdAt,
            Integer voteCount
    ) {
        this.postUuid = postUuid;
        this.contestId = contestId;
        this.memberUuid = memberUuid;
        this.media = media;
        this.createdAt = createdAt;
        this.voteCount = voteCount;
    }

    public static ContestPostResponseDto toDto(ContestPost post) {
        return ContestPostResponseDto.builder()
                .contestId(post.getContestId())
                .postUuid(post.getPostUuid())
                .memberUuid(post.getMemberUuid())
                .media(post.getMedia())
                .createdAt(post.getCreatedAt())
                .voteCount(post.getVoteCount())
                .build();
    }
}
