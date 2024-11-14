package com.mulmeong.feed.api.dto.in;

import com.mulmeong.feed.api.domain.Feed;
import com.mulmeong.feed.api.domain.FeedHashtag;
import com.mulmeong.feed.api.domain.model.Hashtag;
import com.mulmeong.feed.api.vo.in.UpdateFeedRequestVo;
import java.util.List;
import lombok.Builder;
import lombok.Getter;

@Getter
public class UpdateFeedRequestDto {

    private String feedUuid;
    private String memberUuid;
    private String title;
    private String content;
    private Long categoryId;
    private List<Hashtag> hashtags;

    public static UpdateFeedRequestDto toDto(UpdateFeedRequestVo requestVo, String feedUuid,
        String memberUuid) {
        return UpdateFeedRequestDto.builder()
            .feedUuid(feedUuid)
            .memberUuid(memberUuid)
            .title(requestVo.getTitle())
            .content(requestVo.getContent())
            .categoryId(requestVo.getCategoryId())
            .hashtags(requestVo.getHashtags())
            .build();
    }

    public Feed toFeedEntity(Feed existingFeed) {   // don't update visibility
        return Feed.builder()
            .id(existingFeed.getId())
            .feedUuid(feedUuid)
            .memberUuid(memberUuid)
            .title(title)
            .content(content)
            .categoryId(categoryId)
            .visibility(existingFeed.getVisibility())
            .build();
    }

    public List<FeedHashtag> toFeedHashtagEntities() {
        if (hashtags == null) {
            return List.of();
        }

        return hashtags.stream()
            .map(hashtag -> FeedHashtag.builder()
                .feedUuid(feedUuid)
                .name(hashtag.getName())
                .build())
            .toList();
    }

    @Builder
    public UpdateFeedRequestDto(String feedUuid, String memberUuid, String title, String content,
        Long categoryId, List<Hashtag> hashtags) {
        this.feedUuid = feedUuid;
        this.memberUuid = memberUuid;
        this.title = title;
        this.content = content;
        this.categoryId = categoryId;
        this.hashtags = hashtags;
    }

}
