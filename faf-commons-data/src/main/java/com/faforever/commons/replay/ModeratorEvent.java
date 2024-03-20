package com.faforever.commons.replay;

import lombok.Data;

import java.time.Duration;

@Data
public class ModeratorEvent {

  private final Duration time;
  private final String sender;
  private final String message;
  private final int activeCommandSource;

  public ModeratorEvent(Duration time, String sender, String message, int activeCommandSource) {
    this.time = time;
    this.sender = sender;
    this.message = message;
    this.activeCommandSource = activeCommandSource;
  }
}
