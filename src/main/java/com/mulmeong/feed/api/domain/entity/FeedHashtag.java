package com.mulmeong.feed.api.domain.entity;

import com.mulmeong.feed.api.domain.model.Hashtag;
import jakarta.persistence.Id;
import java.util.List;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.data.mongodb.core.mapping.Document;

@Getter
@NoArgsConstructor
@Document(collection = "feed_hashtag")
public class FeedHashtag {

    @Id
    private String id;
    private String feedUuid;
    private List<Hashtag> hashtags;

    @Builder
    public FeedHashtag(String id, String feedUuid, List<Hashtag> hashtags) {
        this.id = id;
        this.feedUuid = feedUuid;
        this.hashtags = hashtags;
    }

}
