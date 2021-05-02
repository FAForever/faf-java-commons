package com.faforever.commons.replay;

import lombok.Value;

import java.util.List;

@Value
public class ReplayData {

  ReplayMetadata metadata;
  byte[] rawData;
  List<ChatMessage> chatMessages;
  List<GameOption> gameOptions;
}
