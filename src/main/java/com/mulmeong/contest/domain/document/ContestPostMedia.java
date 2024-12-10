package com.mulmeong.contest.domain.document;

import com.mulmeong.contest.domain.model.MediaInfo;
import com.mulmeong.contest.domain.model.MediaSubtype;
import com.mulmeong.contest.domain.model.MediaType;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Map;

@Getter
@NoArgsConstructor
@Document
public class ContestPostMedia {

    @Id
    private String mediaUuid;
    private String postUuid;
    private MediaType mediaType;
    private Map<MediaSubtype, MediaInfo> assets;

    @Builder
    public ContestPostMedia(String mediaUuid, String postUuid, MediaType mediaType,
                     Map<MediaSubtype, MediaInfo> assets) {

        this.mediaUuid = mediaUuid;
        this.postUuid = postUuid;
        this.mediaType = mediaType;
        this.assets = assets;
    }
}
