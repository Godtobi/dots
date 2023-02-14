package com.ideamasn.transita.model.enums;

import lombok.Getter;

@Getter
public enum LogComment {
    DAILY("CLIENT MADE TOO MANY DAILY CALLS WITHIN THE HOURS %s and %s"),
    HOURLY("CLIENT MADE TOO MANY HOURLY CALLS WITHIN THE HOURS %s and %s");

    private final String comment;

    LogComment(String comment) {
        this.comment = comment;
    }
}
