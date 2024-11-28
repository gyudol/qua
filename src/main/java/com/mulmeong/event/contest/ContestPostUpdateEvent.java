package com.mulmeong.event.contest;

import com.mulmeong.contest.read.entity.ContestPost;
import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Data
@Getter
@NoArgsConstructor
public class ContestPostUpdateEvent {

    private String postUuid;
    private Integer count;

    public ContestPost toEntity(ContestPost contestPost, Integer count) {
        return ContestPost.builder()
                .id(contestPost.getId())
                .postUuid(postUuid)
                .contestId(contestPost.getContestId())
                .memberUuid(contestPost.getMemberUuid())
                .mediaUrl(contestPost.getMediaUrl())
                .mediaType(contestPost.getMediaType())
                .createdAt(contestPost.getCreatedAt())
                .voteCount(contestPost.getVoteCount() + count)
                .build();
    }

}
