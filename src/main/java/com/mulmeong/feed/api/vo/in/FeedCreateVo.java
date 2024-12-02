package com.mulmeong.feed.api.vo.in;

import com.mulmeong.feed.api.domain.model.Hashtag;
import com.mulmeong.feed.api.domain.model.Media;
import com.mulmeong.feed.api.domain.model.Visibility;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.Valid;
import java.util.List;
import lombok.Getter;

@Getter
public class FeedCreateVo {

    private String memberUuid;
    private String title;
    private String content;
    private String categoryName;
    private Visibility visibility;
    private List<@Valid Hashtag> hashtags;
    @Schema(example = """
        [
            {
                "mediaUuid": "string_ex0",
                "mediaType": "IMAGE",
                "assets": {
                    "IMAGE": {
                        "mediaUrl": "string",
                            "description": "string"
                    }
                }
            },
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
            }
        ]""")
    private List<@Valid Media> mediaList;

}
