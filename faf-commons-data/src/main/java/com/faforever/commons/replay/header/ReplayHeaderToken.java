package com.faforever.commons.replay.header;

import java.util.List;

public record ReplayHeaderToken(String gameVersion, String replayVersion, String pathToScenario, boolean cheatsEnabled, int seed,
                                List<Source> sources,
                                byte[] mods,
                                byte[] gameOptions,
                                List<byte[]> playerOptions) {
}
