package com.mulmeong.shorts.read.api.domain.event;

import com.mulmeong.shorts.read.api.domain.document.Shorts;
import com.mulmeong.shorts.read.api.domain.model.Visibility;
import java.time.LocalDateTime;
import lombok.Getter;
import lombok.ToString;

@Getter
@ToString
public class ShortsStatusUpdateEvent {

    private String shortsUuid;
    private Visibility visibility;
    private LocalDateTime updatedAt;

    public Shorts toDocument(Shorts existingShorts) {
        return Shorts.builder()
            .id(existingShorts.getId())
            .shortsUuid(shortsUuid)
            .memberUuid(existingShorts.getMemberUuid())
            .title(existingShorts.getTitle())
            .playtime(existingShorts.getPlaytime())
            .visibility(visibility)
            .hashtags(existingShorts.getHashtags())
            .media(existingShorts.getMedia())
            .likeCount(existingShorts.getLikeCount())
            .dislikeCount(existingShorts.getDislikeCount())
            .netLikes(existingShorts.getNetLikes())
            .commentCount(existingShorts.getCommentCount())
            .createdAt(existingShorts.getCreatedAt())
            .updatedAt(updatedAt)
            .build();
    }

}
