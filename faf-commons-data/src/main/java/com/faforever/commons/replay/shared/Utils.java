package com.faforever.commons.replay.shared;

import com.google.common.io.LittleEndianDataInputStream;
import org.jetbrains.annotations.Contract;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.Map;

public class Utils {

  @Contract(pure = true)
  private static int peek(LittleEndianDataInputStream dataStream) throws IOException {
    dataStream.mark(1);
    int next = dataStream.readUnsignedByte();
    dataStream.reset();
    return next;
  }

  /**
   * Parses a string from the data stream
   * @param dataStream
   * @return
   * @throws IOException
   */
  @Contract(pure = true)
  public static String parseString(LittleEndianDataInputStream dataStream) throws IOException {
    ByteArrayOutputStream out = new ByteArrayOutputStream();
    byte tempByte;
    while ((tempByte = dataStream.readByte()) != 0) {
      out.write(tempByte);
    }
    return out.toString(StandardCharsets.UTF_8);
  }

  /**
   * Parses a Lua table from the data stream
   * @param dataStream
   * @return
   * @throws IOException
   */
  @Contract(pure = true)
  public static LuaTable parseLua(LittleEndianDataInputStream dataStream) throws IOException {
    int type = dataStream.readUnsignedByte();

    final int LUA_NUMBER = 0;
    final int LUA_STRING = 1;
    final int LUA_NIL = 2;
    final int LUA_BOOL = 3;
    final int LUA_TABLE_START = 4;
    final int LUA_TABLE_END = 5;

    switch (type) {
      case LUA_NUMBER -> {
        float value = dataStream.readFloat();
        return new LuaTable.Number(value);
      }

      case LUA_STRING -> {
        String value = parseString(dataStream);
        return new LuaTable.String(value);
      }


      case LUA_NIL -> {
        dataStream.skipBytes(1);
        return new LuaTable.Nil();
      }

      case LUA_BOOL -> {
        boolean value = dataStream.readUnsignedByte() == 0;
        return new LuaTable.Bool(value);
      }

      case LUA_TABLE_START -> {
        Map<String, LuaTable> value = new HashMap<>();
        while (peek(dataStream) != LUA_TABLE_END) {
          LuaTable key = parseLua(dataStream);

          switch (key) {
            case LuaTable.String(String str) -> value.put(str, parseLua(dataStream));

            case LuaTable.Number(float num) -> value.put(String.valueOf(num), parseLua(dataStream));

            default -> throw new IllegalStateException("Unexpected data type: " + type);
          }

          dataStream.mark(1);
        }
        dataStream.skipBytes(1);

        return new LuaTable.Table(value);
      }
      default -> throw new IllegalStateException("Unexpected data type: " + type);
    }
  }
}
