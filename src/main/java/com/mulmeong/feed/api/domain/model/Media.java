package com.mulmeong.feed.api.domain.model;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import java.util.List;
import lombok.Builder;
import lombok.Getter;

@Getter
public class Media {

    @NotBlank(message = "mediaUUID는 필수 입력 항목입니다.")
    private String mediaUuid;
    @NotNull(message = "mediaType은 필수 입력 항목입니다.")
    private MediaType mediaType;
    @NotEmpty(message = "MediaInfo는 필수 입력 항목입니다.")
    private List<@Valid MediaInfo> assets;

    @Builder
    public Media(String mediaUuid, MediaType mediaType, List<MediaInfo> assets) {
        this.mediaUuid = mediaUuid;
        this.mediaType = mediaType;
        this.assets = assets;
    }

}
