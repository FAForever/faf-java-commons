package com.faforever.commons.replay;

import com.faforever.commons.replay.body.Body;
import com.faforever.commons.replay.header.Header;

public record ReplayContainer(ReplayMetadata metadata, Header header, Body body, byte[] bytes) {
}
