package com.mulmeong.contest.dto.in;

import com.mulmeong.contest.entity.MediaType;
import com.mulmeong.contest.entity.ContestPost;
import com.mulmeong.contest.vo.in.PostRequestVo;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.UUID;


@Getter
@NoArgsConstructor
public class PostRequestDto {

    private String postUuid;
    private Long contestId;
    private String memberUuid;
    private String mediaUrl;
    private MediaType mediaType;

    @Builder
    public PostRequestDto(
            Long contestId,
            String memberUuid,
            String mediaUrl,
            MediaType mediaType
    ) {
        this.contestId = contestId;
        this.memberUuid = memberUuid;
        this.mediaUrl = mediaUrl;
        this.mediaType = mediaType;
    }

    public static PostRequestDto toDto(
            String memberUuid,
            Long contestId,
            PostRequestVo requestVo) {
        return PostRequestDto.builder()
                .contestId(contestId)
                .memberUuid(memberUuid)
                .mediaUrl(requestVo.getMediaUrl())
                .mediaType(requestVo.getMediaType())
                .build();
    }

    public ContestPost toEntity() {
        return ContestPost.builder()
                .postUuid(postUuid = UUID.randomUUID().toString())
                .contestId(contestId)
                .memberUuid(memberUuid)
                .mediaUrl(mediaUrl)
                .mediaType(mediaType)
                .build();
    }
}
