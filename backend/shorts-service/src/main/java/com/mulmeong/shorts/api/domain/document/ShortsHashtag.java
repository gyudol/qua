package com.mulmeong.shorts.api.domain.document;

import com.mulmeong.shorts.api.domain.model.Hashtag;
import java.util.List;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Getter
@NoArgsConstructor
@Document(collection = "shorts_hashtag")
public class ShortsHashtag {

    @Id
    private String shortsUuid;
    private List<Hashtag> hashtags;

    @Builder
    public ShortsHashtag(String shortsUuid, List<Hashtag> hashtags) {
        this.shortsUuid = shortsUuid;
        this.hashtags = hashtags;
    }
}
