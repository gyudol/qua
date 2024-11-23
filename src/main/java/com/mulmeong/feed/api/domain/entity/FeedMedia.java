package com.mulmeong.feed.api.domain.entity;

import com.mulmeong.feed.api.domain.model.MediaInfo;
import com.mulmeong.feed.api.domain.model.MediaType;
import java.util.List;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Getter
@NoArgsConstructor
@Document(collection = "feed_media")
public class FeedMedia {

    @Id
    private String mediaUuid;
    private String feedUuid;
    private MediaType mediaType;
    private List<MediaInfo> assets;

    @Builder
    public FeedMedia(String mediaUuid, String feedUuid, MediaType mediaType,
        List<MediaInfo> assets) {

        this.mediaUuid = mediaUuid;
        this.feedUuid = feedUuid;
        this.mediaType = mediaType;
        this.assets = assets;
    }
}
