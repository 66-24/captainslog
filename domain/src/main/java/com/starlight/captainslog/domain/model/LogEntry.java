package com.starlight.captainslog.domain.model;

import java.time.LocalDateTime;

public record LogEntry(LogEntryId id, String title, String category, LocalDateTime createdDateTime) {
    public LogEntry(LogEntryId id, String title, String category) {
        this(id, title, category, LocalDateTime.now());
    }
}
