package com.faforever.commons.replay.header.token;

import com.faforever.commons.replay.header.Source;

import java.util.List;

public record Token(String gameVersion, String replayVersion, String pathToScenario, boolean cheatsEnabled, int seed,
                    List<Source> sources,
                    byte[] mods,
                    byte[] gameOptions,
                    List<byte[]> playerOptions) {
}
