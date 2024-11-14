package com.mulmeong.feed.api.dto.in;

import com.mulmeong.feed.api.domain.Feed;
import com.mulmeong.feed.api.domain.model.Visibility;
import com.mulmeong.feed.api.vo.in.UpdateFeedStatusRequestVo;
import lombok.Builder;
import lombok.Getter;

@Getter
public class UpdateFeedStatusRequestDto {

    private String feedUuid;
    private String memberUuid;
    private Visibility visibility;

    public static UpdateFeedStatusRequestDto toDto(UpdateFeedStatusRequestVo requestVo,
        String feedUuid, String memberUuid) {
        return UpdateFeedStatusRequestDto.builder()
            .feedUuid(feedUuid)
            .memberUuid(memberUuid)
            .visibility(requestVo.getVisibility())
            .build();
    }

    public Feed toFeedEntity(Feed existingFeed) {   // update visibility
        return Feed.builder()
            .id(existingFeed.getId())
            .feedUuid(feedUuid)
            .memberUuid(memberUuid)
            .title(existingFeed.getTitle())
            .content(existingFeed.getContent())
            .categoryId(existingFeed.getCategoryId())
            .visibility(visibility)
            .build();
    }

    @Builder
    public UpdateFeedStatusRequestDto(String feedUuid, String memberUuid, Visibility visibility) {
        this.feedUuid = feedUuid;
        this.memberUuid = memberUuid;
        this.visibility = visibility;
    }

}
