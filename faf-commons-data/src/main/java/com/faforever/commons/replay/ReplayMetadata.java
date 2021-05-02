package com.faforever.commons.replay;

import lombok.Data;

import java.util.List;
import java.util.Map;

/**
 * This class is meant to be serialized/deserialized from/to JSON.
 */
@Data
public class ReplayMetadata {
  private CompressionType compression;
  private String host;
  private Integer uid;
  private String title;
  private String mapname;
  private GameStatus state;
  //TODO what is this?
  private Boolean[] options;
  // FAF calls this "game_type" but it's actually the victory condition.
  private VictoryCondition gameType;
  private String featuredMod;
  private Integer maxPlayers;
  private Integer numPlayers;
  private Map<String, String> simMods;
  private Map<String, List<String>> teams;
  private Map<String, Integer> featuredModVersions;
  private boolean complete;
  private String recorder;
  private Map<String, String> versionInfo;
  private double gameEnd;
  /**
   * Backwards compatibility: If 0.0, then {@code launchedAt} should be available instead.
   */
  private double gameTime;
  /**
   * Backwards compatibility: If 0.0, then {@code gameTime} should be available instead.
   */
  private double launchedAt;
}
