package com.faforever.commons.replay.body;

import com.faforever.commons.replay.shared.LuaTable;
import com.faforever.commons.replay.shared.Utils;
import com.google.common.io.LittleEndianDataInputStream;
import org.jetbrains.annotations.Contract;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.util.*;

public class ReplayBodyParser {

  @Contract(pure = true)
  public static List<ReplayBodyEvent> parseTokens(List<ReplayBodyToken> tokens) throws IOException {
    return tokens.stream().parallel().map((token) -> {
      try {
        return parseToken(token);
      } catch (Exception exception) {
        return new ReplayBodyEvent.ProcessingError(token, exception);
      }
    }).toList();
  }

  @Contract(pure = true)
  private static ReplayBodyEvent.CommandUnits parseCommandUnits(LittleEndianDataInputStream stream) throws IOException {
    int unitCount = stream.readInt();
    ArrayList<Integer> unitIds = new ArrayList<>(unitCount);
    for (int k = 0; k < unitCount; k++) {
      unitIds.add(stream.readInt());
    }

    return new ReplayBodyEvent.CommandUnits(unitCount, unitIds);
  }

  @Contract(pure = true)
  private static ReplayBodyEvent.CommandFormation parseCommandFormation(LittleEndianDataInputStream stream) throws IOException {
    float orientation = 0;
    float px = 0;
    float py = 0;
    float pz = 0;
    float scale = 0;

    int formation = stream.readInt();
    if (formation != -1) {
      orientation = stream.readFloat();
      px = stream.readFloat();
      py = stream.readFloat();
      pz = stream.readFloat();
      scale = stream.readFloat();
    }

    return new ReplayBodyEvent.CommandFormation(formation, orientation, px, py, pz, scale);
  }

  @Contract(pure = true)
  private static ReplayBodyEvent.CommandTarget parseCommandTarget(LittleEndianDataInputStream stream) throws IOException {
    CommandTargetType target = CommandTargetType.values()[stream.readByte()];
    switch (target) {
      case ENTITY -> {
        int entityId = stream.readInt();
        return new ReplayBodyEvent.CommandTarget.Entity(entityId);
      }

      case POSITION -> {
        float px = stream.readFloat();
        float py = stream.readFloat();
        float pz = stream.readFloat();
        return new ReplayBodyEvent.CommandTarget.Position(px, py, pz);
      }

      default -> {
        return null;
      }
    }
  }

  @Contract(pure = true)
  private static ReplayBodyEvent.CommandData parseCommandData(LittleEndianDataInputStream stream) throws IOException {
    int commandId = stream.readInt();
    byte[] arg1 = stream.readNBytes(4);
    ReplayBodyEventCommandType commandType = ReplayBodyEventCommandType.values()[stream.readByte()];
    byte[] arg2 = stream.readNBytes(4);

    ReplayBodyEvent.CommandTarget commandTarget = parseCommandTarget(stream);

    byte[] arg3 = stream.readNBytes(1);

    ReplayBodyEvent.CommandFormation commandFormation = parseCommandFormation(stream);

    String blueprintId = Utils.readString(stream);
    byte[] arg4 = stream.readNBytes(12);
    byte[] arg5 = new byte[0];

    LuaTable parametersLua = Utils.parseLua(stream);
    if (!(parametersLua instanceof LuaTable.Nil)) {
      arg5 = stream.readNBytes(1);
    }

    return new ReplayBodyEvent.CommandData(
      commandId, commandType, commandTarget, commandFormation, blueprintId, parametersLua
    );
  }

  @Contract(pure = true)
  private static ReplayBodyEvent parseToken(ReplayBodyToken token) throws IOException {

    try (LittleEndianDataInputStream stream = new LittleEndianDataInputStream((new ByteArrayInputStream(token.tokenContent())))) {
      return switch (token.tokenId()) {
        case CMDST_ADVANCE -> {
          int ticks = stream.readInt();
          yield new ReplayBodyEvent.Advance(ticks);
        }

        case CMDST_SET_COMMAND_SOURCE -> {
          int playerIndex = stream.readByte();
          yield new ReplayBodyEvent.SetCommandSource(playerIndex);
        }

        case CMDST_COMMAND_SOURCE_TERMINATED -> new ReplayBodyEvent.CommandSourceTerminated();

        case CMDST_VERIFY_CHECKSUM -> {
          String hash = HexFormat.of().formatHex(stream.readNBytes(16));
          int tick = stream.readInt();

          yield new ReplayBodyEvent.VerifyChecksum(hash, tick);
        }

        case CMDST_REQUEST_PAUSE -> new ReplayBodyEvent.RequestPause();

        case CMDST_RESUME -> new ReplayBodyEvent.RequestResume();

        case CMDST_SINGLE_STEP -> new ReplayBodyEvent.SingleStep();

        case CMDST_CREATE_UNIT -> {
          int playerIndex = stream.readByte();
          String blueprintId = Utils.readString(stream);
          float px = stream.readFloat();
          float pz = stream.readFloat();
          float heading = stream.readFloat();

          yield new ReplayBodyEvent.CreateUnit(playerIndex, blueprintId, px, pz, heading);
        }

        case CMDST_CREATE_PROP -> {
          String blueprintId = Utils.readString(stream);
          float px = stream.readFloat();
          float pz = stream.readFloat();
          float heading = stream.readFloat();

          yield new ReplayBodyEvent.CreateProp(blueprintId, px, pz, heading);
        }

        case CMDST_DESTROY_ENTITY -> {
          int entityId = stream.readInt();
          yield new ReplayBodyEvent.DestroyEntity(entityId);
        }

        case CMDST_WARP_ENTITY -> {
          int entityId = stream.readInt();
          float px = stream.readFloat();
          float py = stream.readFloat();
          float pz = stream.readFloat();
          yield new ReplayBodyEvent.WarpEntity(entityId, px, py, pz);
        }

        case CMDST_PROCESS_INFO_PAIR -> {
          int entityId = stream.read();
          String arg1 = Utils.readString(stream);
          String arg2 = Utils.readString(stream);
          yield new ReplayBodyEvent.ProcessInfoPair(entityId, arg1, arg2);
        }

        case CMDST_ISSUE_COMMAND -> {
          ReplayBodyEvent.CommandUnits commandUnits = parseCommandUnits(stream);
          ReplayBodyEvent.CommandData commandData = parseCommandData(stream);

          yield new ReplayBodyEvent.IssueCommand(commandUnits, commandData);
        }

        case CMDST_ISSUE_FACTORY_COMMAND -> {
          ReplayBodyEvent.CommandUnits commandUnits = parseCommandUnits(stream);
          ReplayBodyEvent.CommandData commandData = parseCommandData(stream);

          yield new ReplayBodyEvent.IssueFactoryCommand(commandUnits, commandData);
        }

        case CMDST_INCREASE_COMMAND_COUNT -> {
          int commandId = stream.readInt();
          int delta = stream.readInt();
          yield new ReplayBodyEvent.IncreaseCommandCount(commandId, delta);
        }

        case CMDST_DECRASE_COMMAND_COUNT -> {
          int commandId = stream.readInt();
          int delta = stream.readInt();
          yield new ReplayBodyEvent.DecreaseCommandCount(commandId, delta);
        }

        case CMDST_SET_COMMAND_TARGET -> {
          int commandId = stream.readInt();
          ReplayBodyEvent.CommandTarget commandTarget = parseCommandTarget(stream);
          yield new ReplayBodyEvent.SetCommandTarget(commandId, commandTarget);
        }

        case CMDST_SET_COMMAND_TYPE -> {
          int commandId = stream.readInt();
          int targetCommandType = stream.readInt();
          yield new ReplayBodyEvent.SetCommandType(commandId, targetCommandType);
        }

        case CMDST_SET_COMMAND_CELLS -> {
          int commandId = stream.readInt();
          LuaTable parametersLua = Utils.parseLua(stream);
          if (!(parametersLua instanceof LuaTable.Nil)) {
            stream.readNBytes(1);
          }

          float px = stream.readFloat();
          float py = stream.readFloat();
          float pz = stream.readFloat();

          yield new ReplayBodyEvent.SetCommandCells(commandId, parametersLua, px, py, pz);
        }

        case CMDST_REMOVE_COMMAND_FROM_QUEUE -> {
          int commandId = stream.readInt();
          int unitId = stream.readInt();
          yield new ReplayBodyEvent.RemoveCommandFromQueue(commandId, unitId);
        }

        case CMDST_DEBUG_COMMAND -> new ReplayBodyEvent.Unprocessed(token, "CMDST_DEBUG_COMMAND");

        case CMDST_EXECUTE_LUA_IN_SIM -> {
          String luaCode = Utils.readString(stream);
          yield new ReplayBodyEvent.ExecuteLuaInSim(luaCode);
        }

        case CMDST_LUA_SIM_CALLBACK -> {
          String func = Utils.readString(stream);
          LuaTable args = Utils.parseLua(stream);
          ReplayBodyEvent.CommandUnits commandUnits = null;

          // suspicion that this is just flat out wrong! Whether there's a selection in the data is not related to whether there are Lua arguments
          if (!(args instanceof LuaTable.Nil)) {
            commandUnits = parseCommandUnits(stream);
          } else {
            // the '4' we read here is the size, I suspect the 3 bytes are maybe to align the data somehow? No idea
            stream.readNBytes(4 + 3);
          }

          yield new ReplayBodyEvent.LuaSimCallback(func, args, commandUnits);
        }

        case CMDST_END_GAME -> new ReplayBodyEvent.EndGame();

        case null -> new ReplayBodyEvent.Unprocessed(token, "Unknown");
      };
    }
  }

  private enum CommandTargetType {
    // Order is crucial
    NONE,
    ENTITY,
    POSITION
  }
}
