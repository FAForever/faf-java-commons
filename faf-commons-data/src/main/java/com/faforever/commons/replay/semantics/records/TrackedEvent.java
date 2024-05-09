package com.faforever.commons.replay.semantics.records;

import com.faforever.commons.replay.body.Event;

public record TrackedEvent(int tick, int authorisedByClient, Event event) {
}
