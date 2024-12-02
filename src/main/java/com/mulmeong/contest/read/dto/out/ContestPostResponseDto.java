package com.mulmeong.contest.read.dto.out;

import com.mulmeong.contest.read.dto.model.BasePaginationRequestDto;
import com.mulmeong.contest.read.entity.ContestPost;
import com.mulmeong.contest.read.entity.MediaType;
import com.mulmeong.contest.read.entity.SortType;
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
    private String mediaUrl;
    private MediaType mediaType;
    private LocalDateTime createdAt;
    private Integer voteCount;

    @Builder
    public ContestPostResponseDto(
            String postUuid,
            Long contestId,
            String memberUuid,
            String mediaUrl,
            MediaType mediaType,
            LocalDateTime createdAt,
            Integer voteCount
    ) {
        this.postUuid = postUuid;
        this.contestId = contestId;
        this.memberUuid = memberUuid;
        this.mediaUrl = mediaUrl;
        this.mediaType = mediaType;
        this.createdAt = createdAt;
        this.voteCount = voteCount;
    }

    public static ContestPostResponseDto toDto(ContestPost post) {
        return ContestPostResponseDto.builder()
                .contestId(post.getContestId())
                .postUuid(post.getPostUuid())
                .memberUuid(post.getMemberUuid())
                .mediaUrl(post.getMediaUrl())
                .mediaType(post.getMediaType())
                .createdAt(post.getCreatedAt())
                .voteCount(post.getVoteCount())
                .build();
    }
}
