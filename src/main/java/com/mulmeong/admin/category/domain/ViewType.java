package com.mulmeong.admin.category.domain;


import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;

public enum ViewType {

    FEED, COMPACT;

    @JsonValue
    public String getString() {
        return name().toLowerCase();
    }

    @JsonCreator
    public static ViewType fromString(String value) {
        for (ViewType viewType : ViewType.values()) {
            if (viewType.name().equalsIgnoreCase(value)) {
                return viewType;
            }
        }
        throw new IllegalArgumentException("Unknown ViewType: " + value);
    }
}