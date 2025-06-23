package com.starlight.captainslog.domain.model;

import org.jspecify.annotations.Nullable;

public record LogEntryId(java.util.@Nullable UUID id) {

    public LogEntryId(java.util.@Nullable UUID id) {
        if (id == null) {
            throw new IllegalArgumentException("LogEntryId cannot be null");
        }
        this.id = id;
    }

    @Override
    public String toString() {
        if (id == null) {
            throw new IllegalStateException("LogEntryId is not initialized");
        }
        return id.toString();
    }
}
