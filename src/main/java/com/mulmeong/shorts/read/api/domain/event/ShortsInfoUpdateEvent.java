package com.mulmeong.shorts.read.api.domain.event;

import com.mulmeong.shorts.read.api.domain.document.Shorts;
import java.time.LocalDateTime;
import lombok.Getter;
import lombok.ToString;

@Getter
@ToString
public class ShortsInfoUpdateEvent {

    private String shortsUuid;
    private String title;
    private Short playtime;
    private LocalDateTime updatedAt;

    public Shorts toDocument(Shorts existingShorts) {
        return Shorts.builder()
            .id(existingShorts.getId())
            .shortsUuid(shortsUuid)
            .memberUuid(existingShorts.getMemberUuid())
            .title(title)
            .playtime(playtime)
            .visibility(existingShorts.getVisibility())
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
