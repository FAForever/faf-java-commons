package com.faforever.commons.replay.body;

import com.faforever.commons.replay.shared.LuaTable;

import java.util.List;

public sealed interface ReplayBodyEvent {

  /**
   * Created by the parser to indicate we do not support this event yet.
   */
  record Unprocessed(ReplayBodyToken token, String reason) implements ReplayBodyEvent {
  }

  /**
   * Created by the parser to indicate we made a mistake
   */
  record ProcessingError(ReplayBodyToken token, Exception exception) implements ReplayBodyEvent {
  }

  /**
   * Created by the engine to advance the tick.
   */
  record Advance(int ticksToAdvance) implements ReplayBodyEvent {
  }

  /**
   * Created by the engine to set the command source for the next tokens.
   */
  record SetCommandSource(int playerIndex) implements ReplayBodyEvent {
  }

  /**
   * Created by the engine when a player leaves the game.
   */
  record CommandSourceTerminated() implements ReplayBodyEvent {
  }

  /**
   * Created by the engine to check the state of the game
   */
  record VerifyChecksum(String hash, int tick) implements ReplayBodyEvent {
  }

  /**
   * Created by the User global `SessionRequestPause` to request a pause
   */
  record RequestPause() implements ReplayBodyEvent {
  }

  /**
   * Created by the User global `SessionResume` to request a resume
   */
  record RequestResume() implements ReplayBodyEvent {
  }

  /**
   * Created by the console command `wld_SingleStep` while the game is paused
   */
  record SingleStep() implements ReplayBodyEvent {
  }

  /**
   * Created by the console command `CreateUnit`
   */
  record CreateUnit(int playerIndex, String blueprintId, float px, float pz, float heading) implements ReplayBodyEvent {
  }

  /**
   * Created by the console command `CreateProp`
   */
  record CreateProp(String blueprintId, float px, float pz, float heading) implements ReplayBodyEvent {
  }

  /**
   * Created by the console commands `DestroySelectedUnits` and `DestroySelectedUnits`
   */
  record DestroyEntity(int entityId) implements ReplayBodyEvent {
  }

  /**
   * Created by the console command `TeleportSelectedUnits`
   */
  record WarpEntity(int entityId, float px, float py, float pz) implements ReplayBodyEvent {
  }

  /**
   * Created by the UserUnit function `ProcessInfo`
   */
  record ProcessInfoPair(int entityId, String arg1, String arg2) implements ReplayBodyEvent {
  }

  /**
   * Created by the engine when the user creates a command by clicking
   */
  record IssueCommand(CommandUnits commandUnits, CommandData commandData) implements ReplayBodyEvent {
  }

  /**
   * Created by the User global function `IssueBlueprintCommand`
   */
  record IssueFactoryCommand(CommandUnits commandUnits, CommandData commandData) implements ReplayBodyEvent {
  }

  /**
   * Created by the User global function `IncreaseBuildCountInQueue`
   */
  record IncreaseCommandCount(int commandId, int delta) implements ReplayBodyEvent {
  }

  /**
   * Created by the user global function `DecreaseBuildCountInQueue`
   */
  record DecreaseCommandCount(int commandId, int delta) implements ReplayBodyEvent {
  }

  /**
   * Created by the engine when updating the target (entity or position) of a command
   */
  record SetCommandTarget(int commandId, CommandTarget commandTarget) implements ReplayBodyEvent {
  }

  /**
   * Created by the engine when transforming the command (move to patrol)
   */
  record SetCommandType(int commandId, int targetId) implements ReplayBodyEvent {
  }

  /**
   * ??
   */
  record SetCommandCells(int commandId, LuaTable parametersLua, float px, float py, float pz) implements ReplayBodyEvent {
  }

  /**
   * Created by the User global function `DeleteCommand`
   */
  record RemoveCommandFromQueue(int commandId, int unitId) implements ReplayBodyEvent {
  }

  /**
   * ??
   */
  record DebugCommand() implements ReplayBodyEvent {
  }

  /**
   * Created by the User global function `ExecLuaInSim`
   */
  record ExecuteLuaInSim(String luaCode) implements ReplayBodyEvent {
  }

  /**
   * Created by the user global function `SimCallback`
   */
  record LuaSimCallback(String func, LuaTable parametersLua, CommandUnits commandUnits) implements ReplayBodyEvent {
  }

  /**
   * Created by the User global function `SessionEndGame`
   */
  record EndGame() implements ReplayBodyEvent {
  }

  sealed interface CommandTarget {
    record Entity(int unitId) implements CommandTarget {
    }

    record Position(float px, float py, float pz) implements CommandTarget {
    }
  }

  record CommandUnits(int count, List<Integer> unitIds) {
  }

  record CommandFormation(int formationId, float orientation, float px, float py, float pz, float scale) {
  }

  /**
   * @param commandId        The command id to reference it later (to adjust or delete it, for example)
   * @param commandType      The command type (move, attack - what else?)
   * @param commandTarget
   * @param commandFormation
   * @param blueprintId
   * @param parametersLua
   */
  record CommandData(int commandId, ReplayBodyEventCommandType commandType, CommandTarget commandTarget,
                     CommandFormation commandFormation, String blueprintId, LuaTable parametersLua) {
  }
}
