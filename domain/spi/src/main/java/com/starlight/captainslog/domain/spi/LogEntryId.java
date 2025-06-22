package com.starlight.captainslog.domain.spi;

public class LogEntryId {
    private final String id;

    public LogEntryId(String id) {
        this.id = id;
    }

    public LogEntryId(java.util.UUID uuid) {
        this.id = uuid.toString();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof LogEntryId)) return false;
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
