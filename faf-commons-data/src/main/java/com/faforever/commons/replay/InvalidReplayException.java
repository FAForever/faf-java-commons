package com.faforever.commons.replay;

public class InvalidReplayException extends RuntimeException {
  public InvalidReplayException(String message) {
    super(message);
  }

  public InvalidReplayException(String message, Exception e) {
    super(message, e);
  }
}
