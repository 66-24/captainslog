package com.starlight.captainslog.domain.spi;

import java.util.UUID;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class LogEntryIdTest {
    @Test
    void shouldWrapUuidAndCompareByValue() {
        UUID uuid = UUID.randomUUID();
        LogEntryId id1 = new LogEntryId(uuid);
        LogEntryId id2 = new LogEntryId(uuid);

        assertThat(id1).isEqualTo(id2);
        assertThat(id1.hashCode()).isEqualTo(id2.hashCode());
        assertThat(id1.toString()).isEqualTo(uuid.toString());
    }
}
