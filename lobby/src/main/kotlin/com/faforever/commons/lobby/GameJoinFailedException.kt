package com.faforever.commons.lobby

class GameJoinFailedException(private val gameId: Int) : RuntimeException("Failed to join game with id: $gameId") {
  fun getGameId(): Int {
    return gameId
  }
}
