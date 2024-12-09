package com.mulmeong.shorts.api.domain.document;

import com.mulmeong.shorts.api.domain.model.MediaInfo;
import com.mulmeong.shorts.api.domain.model.MediaType;
import java.util.Map;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Getter
@NoArgsConstructor
@Document(collection = "shorts_media")
public class ShortsMedia {

    @Id
    private String mediaUuid;
    private String shortsUuid;
    private Map<MediaType, MediaInfo> assets;

    @Builder
    public ShortsMedia(String mediaUuid, String shortsUuid, Map<MediaType, MediaInfo> assets) {
        this.mediaUuid = mediaUuid;
        this.shortsUuid = shortsUuid;
        this.assets = assets;
    }
}
