package com.mulmeong.shorts.api.vo.in;

import com.mulmeong.shorts.api.domain.model.Hashtag;
import com.mulmeong.shorts.api.domain.model.Media;
import com.mulmeong.shorts.api.domain.model.Visibility;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.Valid;
import java.util.List;
import lombok.Getter;

@Getter
public class ShortsCreateVo {

    private String memberUuid;
    private String title;
    private Short playtime;
    private Visibility visibility;
    private List<@Valid Hashtag> hashtags;
    @Schema(example = """
        {
            "mediaUuid": "string_ex1",
            "assets": {
                "VIDEO_THUMBNAIL": {
                    "mediaUrl": "string",
                    "description": "string"
                },
                "STREAMING_360": {
                    "mediaUrl": "string",
                    "description": "string"
                },
                "STREAMING_540": {
                    "mediaUrl": "string",
                    "description": "string"
                },
                "STREAMING_720": {
                    "mediaUrl": "string",
                    "description": "string"
                },
                "VIDEO_MP4": {
                    "mediaUrl": "string",
                    "description": "string"
                }
            }
        }""")
    private @Valid Media media;
}
