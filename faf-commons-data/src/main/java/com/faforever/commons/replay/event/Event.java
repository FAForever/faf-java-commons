package com.faforever.commons.replay.event;

import com.faforever.commons.replay.token.Token;

import java.util.List;

public sealed interface Event {

  /**
   * Created by the parser to indicate we do not support this event yet.
   */
  record Unprocessed(Token token, String reason) implements Event { }

  /**
   * Created by the parser to indicate we made a mistake
   */
  record ProcessingError(Token token, Exception exception) implements Event { }

  /**
   * Created by the engine to advance the tick.
   */
  record Advance(int ticksToAdvance) implements Event { }

  /**
   * Created by the engine to set the command source for the next tokens.
   */
  record SetCommandSource(int playerIndex) implements Event { }

  /**
   * Created by the engine when a player leaves the game.
   */
  record CommandSourceTerminated() implements Event { }

  /**
   * Created by the engine to check the state of the game
   */
  record VerifyChecksum(String hash, int tick) implements Event { }

  /**
   * Created by the User global `SessionRequestPause` to request a pause
   */
  record RequestPause() implements Event { }

  /**
   * Created by the User global `SessionResume` to request a resume
   */
  record RequestResume() implements Event { }

  /**
   * Created by the console command `wld_SingleStep` while the game is paused
   */
  record SingleStep() implements Event { }

  /**
   * Created by the console command `CreateUnit`
   */
  record CreateUnit(int playerIndex, String blueprintId, float px, float pz, float heading) implements Event { }

  /**
   * Created by the console command `CreateProp`
   */
  record CreateProp(String blueprintId, float px, float pz, float heading) implements Event { }

  /**
   * Created by the console commands `DestroySelectedUnits` and `DestroySelectedUnits`
   */
  record DestroyEntity(int entityId) implements Event { }

  /**
   * Created by the console command `TeleportSelectedUnits`
   */
  record WarpEntity(int entityId, float px, float py, float pz) implements Event { }

  /**
   * Created by the UserUnit function `ProcessInfo`
   */
  record ProcessInfoPair(int entityId, String arg1, String arg2) implements Event { }

  enum CommandTargetType {
    NONE,
    ENTITY,
    POSITION
  }

  enum CommandType {
    NONE("NONE"),
    STOP("Stop"),
    MOVE("Move"),
    DIVE("Dive"),
    FORM_MOVE("FormMove"),
    BUILD_SILO_TACTICAL("BuildSiloTactical"),
    BUILD_SILO_NUKE("BuildSiloNuke"),
    BUILD_FACTORY("BuildFactory"),
    BUILD_MOBILE("BuildMobile"),
    BUILD_ASSIsT("BuildAssist"),
    ATTACK("Attack"),
    FORM_ATTACK("FormAttack"),
    NUKE("Nuke"),
    TACTICAL("Tactical"),
    TELEPORT("Teleport"),
    GUARD("Guard"),
    PATROL("Patrol"),
    FERRY("Ferry"),
    FORM_PATROL("FormPatrol"),
    RECLAIM("Reclaim"),
    REPAIR("Repair"),
    CAPTURE("Capture"),
    TRANSPORT_LOAD_UNITS("TransportLoadUnits"),
    TRANSPORT_REVERSE_LOAD_UNITS("TransportReverseLoadUnits"),
    TRANSPORT_UNLOAD_UNITS("TransportUnloadUnits"),
    TRANSPORT_UNLOAD_SPECIFIC_UNITS("TransportUnloadSpecificUnits"),
    DETACH_FROM_TRANSPORT("DetachFromTransport"),
    UPGRADE("Upgrade"),
    SCRIPT("Script"),
    ASSIST_COMMANDER("AssistCommander"),
    KILL_SELF("KillSelf"),
    DESTROY_SELF("DestroySelf"),
    SACRIFICE("Sacrifice"),
    PAUSE("Pause"),
    OVER_CHARGE("OverCharge"),
    AGGRESSIVE_MOVE("AggressiveMove"),
    FORM_AGGRESSIVE_MOVE("FormAggressiveMove"),
    ASSIST_MOVE("AssistMove"),
    SPECIAL_ACTION("SpecialAction"),
    DOCK("Dock");

    private final String string;

    CommandType(String string) {
      this.string = string;
    }
  }

  record CommandUnits (int count, List<Integer> unitIds) {}

  record CommandFormation (int formationId, float orientation, float px, float py, float pz, float scale) {}

  sealed interface CommandTarget {
    record Entity (int unitId) implements CommandTarget { }
    record Position (float px, float py, float pz) implements CommandTarget { }
  }

  /**
   *
   * @param commandId   The command id to reference it later (to adjust or delete it, for example)
   * @param commandtype   The command type (move, attack - what else?)
   * @param commandTarget
   * @param commandFormation
   * @param blueprintId
   * @param parametersLua
   */
  record CommandData (int commandId, CommandType commandtype, CommandTarget commandTarget, CommandFormation commandFormation, String blueprintId, LuaData parametersLua) {}

  /**
   * Created by the engine when the user creates a command by clicking
   */
  record IssueCommand( CommandUnits commandUnits, CommandData commandData ) implements Event {


  }

  /**
   * Created by the User global function `IssueBlueprintCommand`
   */
  record IssueFactoryCommand(CommandUnits commandUnits, CommandData commandData) implements Event { }

  /**
   * Created by the User global function `IncreaseBuildCountInQueue`
   */
  record IncreaseCommandCount(int commandId, int delta) implements Event { }

  /**
   * Created by the user global function `DecreaseBuildCountInQueue`
   */
  record DecreaseCommandCount(int commandId, int delta) implements Event { }

  /**
   * Created by the engine when updating the target (entity or position) of a command
   */
  record SetCommandTarget(int commandId, CommandTarget commandTarget) implements Event { }

  /**
   * Created by the engine when transforming the command (move to patrol)
   */
  record SetCommandType(int commandId, int targetId) implements Event { }

  /**
   * ??
   */
  record SetCommandCells(int commandId, LuaData parametersLua, float px, float py, float pz) implements Event { }

  /**
   * Created by the User global function `DeleteCommand`
   */
  record RemoveCommandFromQueue(int commandId, int unitId) implements Event { }

  /**
   * ??
   */
  record DebugCommand() implements Event { }

  /**
   * Created by the User global function `ExecLuaInSim`
   */
  record ExecuteLuaInSim(String luaCode) implements Event { }

  /**
   * Created by the user global function `SimCallback`
   */
  record LuaSimCallback(String func, LuaData parametersLua, CommandUnits commandUnits) implements Event { }

  /**
   * Created by the User global function `SessionEndGame`
   */
  record EndGame() implements Event { }

}
