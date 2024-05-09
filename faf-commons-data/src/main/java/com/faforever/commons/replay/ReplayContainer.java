package com.faforever.commons.replay;

import com.faforever.commons.replay.body.Event;
import com.faforever.commons.replay.header.ReplayHeader;
import com.faforever.commons.replay.semantics.records.TrackedEvent;

import java.util.List;

public record ReplayContainer(ReplayMetadata metadata, ReplayHeader header, List<TrackedEvent> trackedEvents) {
}
