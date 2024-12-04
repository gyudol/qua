package com.mulmeong.shorts.api.domain.model;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import java.util.Map;
import lombok.Builder;
import lombok.Getter;

@Getter
public class Media {

    @NotBlank(message = "mediaUUID는 필수 입력 항목입니다.")
    private String mediaUuid;
    @NotEmpty(message = "assets는 필수 입력 항목입니다.")
    private Map<@Valid MediaType, @Valid MediaInfo> assets;

    @Builder
    public Media(String mediaUuid, Map<MediaType, MediaInfo> assets) {
        this.mediaUuid = mediaUuid;
        this.assets = assets;
    }

}
