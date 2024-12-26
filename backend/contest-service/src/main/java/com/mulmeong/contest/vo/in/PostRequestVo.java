package com.mulmeong.contest.vo.in;

import com.mulmeong.contest.domain.model.Media;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.Valid;
import lombok.Getter;


@Getter
public class PostRequestVo {

    @Schema(example = """
        {
            "mediaUuid": "string_ex1",
            "mediaType": "VIDEO",
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
