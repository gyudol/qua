package com.mulmeong.feed.api.domain.model;

import jakarta.validation.constraints.Pattern;
import lombok.Getter;

@Getter
public class Media {

    private String mediaUrl;
    private MediaType mediaType;
    private String description;

}
