package com.starlight.captainslog.domain.model;

import org.jspecify.annotations.Nullable;

public class LogEntryId {
    private final String id;

    public LogEntryId(@Nullable String id) {
        if (id == null || id.isEmpty()) {
            throw new IllegalArgumentException("LogEntryId cannot be null or empty");
        }
        this.id = id;
    }

    public LogEntryId(java.util.@Nullable UUID uuid) {
        if (uuid == null) {
            throw new IllegalArgumentException("LogEntryId cannot be null");
        }
        this.id = uuid.toString();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        // Use pattern matching to ensure the object is of the correct type
        if (o == null || getClass() != o.getClass()) return false;
        
        LogEntryId that = (LogEntryId) o;
        return id.equals(that.id);
    }

    @Override
    public int hashCode() {
        return id.hashCode();
    }

    @Override
    public String toString() {
        return id;
    }
}
