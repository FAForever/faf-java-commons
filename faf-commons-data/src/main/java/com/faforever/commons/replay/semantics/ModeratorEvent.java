package com.faforever.commons.replay.semantics;

import java.time.Duration;

public record ModeratorEvent(Duration time,
                             Integer activeCommandSource,
                             Integer fromArmy,
                             String message,
                             String playerNameFromArmy,
                             String playerNameFromCommandSource) {
}
