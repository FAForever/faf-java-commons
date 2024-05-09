package com.faforever.commons.replay.semantics.records;

import com.faforever.commons.replay.body.Event;
import com.faforever.commons.replay.header.Source;

public record TrackedEvent(int tick, Source source, Event event) {
}
