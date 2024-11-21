package com.mulmeong.feed.api.dto.in;

import com.mulmeong.feed.api.domain.Feed;
import com.mulmeong.feed.api.domain.model.Visibility;
import com.mulmeong.feed.api.vo.in.UpdateFeedStatusRequestVo;
import lombok.Builder;
import lombok.Getter;

@Getter
public class UpdateFeedStatusRequestDto {

    private String feedUuid;
    private Visibility visibility;

    public static UpdateFeedStatusRequestDto toDto(UpdateFeedStatusRequestVo requestVo,
        String feedUuid) {

        return UpdateFeedStatusRequestDto.builder()
            .feedUuid(feedUuid)
            .visibility(requestVo.getVisibility())
            .build();
    }

    public Feed toFeedEntity(Feed existingFeed) {   // update visibility
        return Feed.builder()
            .id(existingFeed.getId())
            .feedUuid(feedUuid)
            .memberUuid(existingFeed.getMemberUuid())
            .title(existingFeed.getTitle())
            .content(existingFeed.getContent())
            .categoryId(existingFeed.getCategoryId())
            .visibility(visibility)
            .build();
    }

    @Builder
    public UpdateFeedStatusRequestDto(String feedUuid, Visibility visibility) {
        this.feedUuid = feedUuid;
        this.visibility = visibility;
    }

}
