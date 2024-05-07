package com.faforever.commons.replay.shared;

import java.util.Map;

public sealed interface LuaTable {

  record Number(float value) implements LuaTable {}
  record String(java.lang.String value) implements LuaTable {}
  record Nil() implements LuaTable {}
  record Table(Map<java.lang.String, LuaTable> value) implements LuaTable {}
  record Bool(boolean value) implements LuaTable {}

}
