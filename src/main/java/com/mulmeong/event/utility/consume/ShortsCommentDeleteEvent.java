package com.mulmeong.event.utility.consume;

import com.mulmeong.batchserver.shorts.domain.document.ShortsRead;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class ShortsCommentDeleteEvent {

    private String shortsUuid;

    public ShortsRead toShortsReadEntity(ShortsRead shorts, Long commentCount) {
        return ShortsRead.builder()
                .id(shorts.getId())
                .shortsUuid(shorts.getShortsUuid())
                .memberUuid(shorts.getMemberUuid())
                .title(shorts.getTitle())
                .playtime(shorts.getPlaytime())
                .visibility(shorts.getVisibility())
                .media(shorts.getMedia())
                .likeCount(shorts.getLikeCount())
                .dislikeCount(shorts.getDislikeCount())
                .netLikes(shorts.getNetLikes())
                .commentCount(commentCount)
                .createdAt(shorts.getCreatedAt())
                .updatedAt(shorts.getUpdatedAt())
                .build();
    }

}
