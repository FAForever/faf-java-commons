package com.faforever.commons.replay.token;

public record Token(TokenId tokenId, int tokenSize, byte[] tokenContent) {

  public enum TokenId {
    CMDST_ADVANCE,
    CMDST_SET_COMMAND_SOURCE,

    /**
     * When a player leaves the game
     */
    CMDST_COMMAND_SOURCE_TERMINATED,
    CMDST_VERIFY_CHECKSUM,
    CMDST_REQUEST_PAUSE,
    CMDST_RESUME,
    CMDST_SINGLE_STEP,
    CMDST_CREATE_UNIT,
    CMDST_CREATE_PROP,
    CMDST_DESTROY_ENTITY,
    CMDST_WARP_ENTITY,
    CMDST_PROCESS_INFO_PAIR,
    CMDST_ISSUE_COMMAND,
    CMDST_ISSUE_FACTORY_COMMAND,
    CMDST_INCREASE_COMMAND_COUNT,
    CMDST_DECRASE_COMMAND_COUNT,

    /**
     * When you adjust the target of an order
     */
    CMDST_SET_COMMAND_TARGET,

    /**
     * When you transform a move order into a patrol order
     */
    CMDST_SET_COMMAND_TYPE,
    CMDST_SET_COMMAND_CELLS,
    CMDST_REMOVE_COMMAND_FROM_QUEUE,
    CMDST_DEBUG_COMMAND,
    CMDST_EXECUTE_LUA_IN_SIM,
    CMDST_LUA_SIM_CALLBACK,
    CMDST_END_GAME
  }
}
