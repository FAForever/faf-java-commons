package com.faforever.commons.lobby

import com.fasterxml.jackson.annotation.JsonProperty

interface LobbyProtocolMessage {
}

enum class Faction {
  @JsonProperty("aeon")
  AEON,

  @JsonProperty("cybran")
  CYBRAN,

  @JsonProperty("uef")
  UEF,

  @JsonProperty("seraphim")
  SERAPHIM,

  @JsonProperty("nomad")
  NOMAD,

  @JsonProperty("civilian")
  CIVILIAN
}

enum class GameStatus {
  @JsonProperty("unknown")
  UNKNOWN,

  @JsonProperty("playing")
  PLAYING,

  @JsonProperty("open")
  OPEN,

  @JsonProperty("closed")
  CLOSED;
}

enum class GameAccess {
  @JsonProperty("public")
  PUBLIC,

  @JsonProperty("password")
  PASSWORD
}

enum class GameVisibility {
  @JsonProperty("public")
  PUBLIC,

  @JsonProperty("friends")
  PRIVATE
}

enum class VictoryCondition {
  @JsonProperty(index = 0)
  DEMORALIZATION,

  @JsonProperty(index = 1)
  DOMINATION,

  @JsonProperty(index = 2)
  ERADICATION,

  @JsonProperty(index = 3)
  SANDBOX,

  @JsonProperty("unknown")
  UNKNOWN
}

enum class LobbyMode {
  @JsonProperty(index = 0)
  DEFAULT_LOBBY,

  @JsonProperty(index = 1)
  AUTO_LOBBY
}
