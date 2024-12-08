package com.mulmeong.shorts.read.api.dto.out;

import com.mulmeong.shorts.read.api.domain.document.Shorts;
import com.mulmeong.shorts.read.api.domain.model.Hashtag;
import com.mulmeong.shorts.read.api.domain.model.Media;
import com.mulmeong.shorts.read.api.domain.model.Visibility;
import java.time.LocalDateTime;
import java.util.List;
import lombok.Builder;
import lombok.Getter;

@Getter
public class ShortsResponseDto {

    private String shortsUuid;
    private String memberUuid;
    private String title;
    private Short playtime;
    private Visibility visibility;
    private List<Hashtag> hashtags;
    private Media media;
    private Long likeCount;
    private Long dislikeCount;
    private Long commentCount;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;

    public static ShortsResponseDto fromDocument(Shorts shorts) {
        return ShortsResponseDto.builder()
            .shortsUuid(shorts.getShortsUuid())
            .memberUuid(shorts.getMemberUuid())
            .title(shorts.getTitle())
            .playtime(shorts.getPlaytime())
            .visibility(shorts.getVisibility())
            .hashtags(shorts.getHashtags())
            .media(shorts.getMedia())
            .likeCount(shorts.getLikeCount())
            .dislikeCount(shorts.getDislikeCount())
            .commentCount(shorts.getCommentCount())
            .createdAt(shorts.getCreatedAt())
            .updatedAt(shorts.getUpdatedAt())
            .build();
    }

    @Builder
    public ShortsResponseDto(String shortsUuid, String memberUuid, String title, Short playtime,
        Visibility visibility, List<Hashtag> hashtags, Media media, Long likeCount,
        Long dislikeCount, Long commentCount, LocalDateTime createdAt, LocalDateTime updatedAt) {

        this.shortsUuid = shortsUuid;
        this.memberUuid = memberUuid;
        this.title = title;
        this.playtime = playtime;
        this.visibility = visibility;
        this.hashtags = hashtags;
        this.media = media;
        this.likeCount = likeCount;
        this.dislikeCount = dislikeCount;
        this.commentCount = commentCount;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
    }

}
