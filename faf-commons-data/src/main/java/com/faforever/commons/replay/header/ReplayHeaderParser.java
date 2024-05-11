package com.faforever.commons.replay.header;

import com.faforever.commons.replay.shared.LuaData;
import com.google.common.io.LittleEndianDataInputStream;
import org.jetbrains.annotations.Contract;
import org.jetbrains.annotations.Nullable;


import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.util.List;
import java.util.Objects;

import static com.faforever.commons.replay.shared.Utils.parseLua;

public class ReplayHeaderParser {

  @Contract(pure = true)
  public static @Nullable ReplayHeader parseHeader(ReplayHeaderToken token) throws IOException {
    if (!Objects.equals(token.replayVersion(), "Replay v1.9")) {
      throw new IOException();
    }

    GameOptions gameOptions = parseGameOptions(token.gameOptions());
    List<GameMod> gameMods = parseMod(token.mods());
    List<PlayerOptions> playerOptions = token.playerOptions().stream().map((bytes) -> {
      try {
        return parsePlayerOptions(bytes);
      } catch (Exception e) {

        return null;
      }
    }).toList();

    return new ReplayHeader(
      token.gameVersion(), token.replayVersion(), token.pathToScenario(), token.cheatsEnabled(), token.seed(),
      token.sources(),
      gameMods,
      gameOptions,
      playerOptions
    );
  }

  @Contract(pure = true)
  private static @Nullable List<GameMod> parseMod(byte[] bytes) throws IOException {
    try (LittleEndianDataInputStream stream = new LittleEndianDataInputStream((new ByteArrayInputStream(bytes)))) {
      LuaData mod = parseLua(stream);

      // TODO: needs implementation

      return null;
    }
  }

  @Contract(pure = true)
  private static @Nullable GameOptions parseGameOptions(byte[] bytes) throws IOException {
    try (LittleEndianDataInputStream stream = new LittleEndianDataInputStream((new ByteArrayInputStream(bytes)))) {
      LuaData gameOptions = parseLua(stream);

      // TODO: needs implementation

      return null;
    }
  }

  @Contract(pure = true)
  private static @Nullable PlayerOptions parsePlayerOptions(byte[] bytes) throws IOException {
    try (LittleEndianDataInputStream stream = new LittleEndianDataInputStream((new ByteArrayInputStream(bytes)))) {
      LuaData playerOptions = parseLua(stream);

      // TODO: needs implementation

      return null;
    }
  }
}
