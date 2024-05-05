package com.faforever.commons.replay.body.event;

import java.util.Map;

public sealed interface LuaData {

  public record Number(float value) implements LuaData {}
  public record String(java.lang.String value) implements LuaData {}
  public record Nil() implements LuaData {}
  public record Table(Map<java.lang.String, LuaData> value) implements LuaData {}
  public record Bool(boolean value) implements LuaData {}

}
