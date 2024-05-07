package com.faforever.commons.replay.header.parse;

import com.faforever.commons.replay.header.GameMod;
import com.faforever.commons.replay.header.GameOptions;
import com.faforever.commons.replay.header.Header;
import com.faforever.commons.replay.header.PlayerOptions;
import com.faforever.commons.replay.header.token.Token;

import com.faforever.commons.replay.shared.LuaTable;
import com.google.common.io.LittleEndianDataInputStream;
import org.jetbrains.annotations.Contract;
import org.jetbrains.annotations.Nullable;


import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.util.List;

import static com.faforever.commons.replay.shared.Utils.parseLua;

public class Parser {

  @Contract(pure = true)
  public static @Nullable Header parseHeader(Token token) throws IOException {

    GameOptions gameOptions = parseGameOptions(token.gameOptions());
    List<GameMod> gameMods = parseMod(token.mods());
    List<PlayerOptions> playerOptions = token.playerOptions().stream().map((bytes) -> {
      try {
        return parsePlayerOptions(bytes);
      } catch (Exception e) {

        return null;
      }
    }).toList();

    return new Header(
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
      LuaTable mod = parseLua(stream);





      return null;
    }
  }

  @Contract(pure = true)
  private static @Nullable GameOptions parseGameOptions(byte[] bytes) throws IOException {
    try (LittleEndianDataInputStream stream = new LittleEndianDataInputStream((new ByteArrayInputStream(bytes)))) {
      LuaTable gameOptions = parseLua(stream);




      return null;
    }
  }

  @Contract(pure = true)
  private static @Nullable PlayerOptions parsePlayerOptions(byte[] bytes) throws IOException {
    try (LittleEndianDataInputStream stream = new LittleEndianDataInputStream((new ByteArrayInputStream(bytes)))) {
      LuaTable playerOptions = parseLua(stream);




      return null;
    }
  }
}
