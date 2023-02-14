package com.ideamasn.transita.model.enums;

import lombok.Getter;

@Getter
public enum DurationType {

    DAILY("DAILY"),
    HOURLY("HOURLY");

    private final String duration;

    DurationType(String duration) {
        this.duration = duration;
    }
}
