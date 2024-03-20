package com.faforever.commons.replay;

import lombok.Data;

import java.time.Duration;

@Data
public class ModeratorEvent {

  private final Duration time;
  private final String sender;
  private final String message;
  private final int activeCommandSource;
}
