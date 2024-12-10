package com.mulmeong.contest.dto.in;

import com.mulmeong.contest.domain.document.ContestPostMedia;
import com.mulmeong.contest.domain.entity.ContestPost;
import com.mulmeong.contest.domain.model.Media;

import com.mulmeong.contest.domain.model.MediaInfo;
import com.mulmeong.contest.domain.model.MediaSubtype;
import com.mulmeong.contest.vo.in.PostRequestVo;
import com.mulmeong.event.contest.produce.ContestPostCreateEvent;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import java.time.LocalDateTime;
import java.util.UUID;


@Slf4j
@Getter
@NoArgsConstructor
public class PostRequestDto {

    private String postUuid;
    private Long contestId;
    private String memberUuid;
    private Media media;
    private LocalDateTime createdAt;

    @Builder
    public PostRequestDto(
            String postUuid,
            Long contestId,
            String memberUuid,
            Media media,
            LocalDateTime createdAt
    ) {
        this.postUuid = postUuid;
        this.contestId = contestId;
        this.memberUuid = memberUuid;
        this.media = media;
        this.createdAt = createdAt;
    }

    public static PostRequestDto toDto(
            String memberUuid,
            Long contestId,
            PostRequestVo requestVo) {
        return PostRequestDto.builder()
                .postUuid(UUID.randomUUID().toString())
                .contestId(contestId)
                .memberUuid(memberUuid)
                .media(requestVo.getMedia())
                .createdAt(LocalDateTime.now())
                .build();
    }

    public ContestPostMedia toMedia() {
        return ContestPostMedia.builder()
                .mediaUuid(media.getMediaUuid())
                .postUuid(postUuid)
                .mediaType(media.getMediaType())
                .assets(media.getAssets())
                .build();
    }

    public ContestPost toEntity() {
        return ContestPost.builder()
                .postUuid(postUuid)
                .contestId(contestId)
                .memberUuid(memberUuid)
                .createdAt(createdAt)
                .build();
    }

    public ContestPostCreateEvent toEvent() {
        return ContestPostCreateEvent.builder()
                .postUuid(postUuid)
                .contestId(contestId)
                .memberUuid(memberUuid)
                .media(media)
                .createdAt(createdAt)
                .build();
    }
}
