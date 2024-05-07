package com.faforever.commons.replay.header;

/**
 * Populated by the engine and it represents the clients that are connected to the game.
 * @param name
 * @param sourceId
 */
public record Source(int sourceId, int PlayerId, String name) {
}
