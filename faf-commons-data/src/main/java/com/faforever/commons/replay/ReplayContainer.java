package com.faforever.commons.replay;

import com.faforever.commons.replay.body.ReplayBody;
import com.faforever.commons.replay.header.ReplayHeader;

public record ReplayContainer(ReplayMetadata metadata, ReplayHeader header, ReplayBody body, byte[] bytes) {
}
