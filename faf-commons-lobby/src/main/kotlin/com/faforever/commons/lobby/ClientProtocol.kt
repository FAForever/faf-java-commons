package com.faforever.commons.lobby

import com.faforever.loadtest.remote.GameAccess
import com.faforever.loadtest.remote.GameVisibility
import com.faforever.loadtest.remote.LobbyProtocolMessage
import com.fasterxml.jackson.annotation.JsonProperty

interface ClientMessage : LobbyProtocolMessage {
  val command: String
}

data class LoginRequest(
  val login: String,
  val password: String,
  val session: Long,
  @JsonProperty("unique_id")
  val uniqueId: String?,
  @JsonProperty("local_ip")
  val localIp: String,
) : ClientMessage {
  override val command: String = "hello"
}

data class SessionRequest(
  val version: String = "1.0",
  @JsonProperty("user_agent")
  val userAgent: String = "downlords-faf-client",
) : ClientMessage {
  override val command: String = "ask_session"
}

enum class MatchmakerState {
  @JsonProperty("start")
  START,

  @JsonProperty("stop")
  STOP
}

data class SearchLadder1v1Request(
  val mod: String = "ladder1v1",
  val state: MatchmakerState = MatchmakerState.START,
  val faction: String,
) : ClientMessage {
  override val command: String = "game_matchmaking"
}

data class HostGameRequest(
  val mapname: String,
  val title: String,
  val mod: String,
  val options: BooleanArray,
  val access: GameAccess,
  val version: Int,
  val password: String?,
  val visibility: GameVisibility,
) : ClientMessage {
  override val command: String = "game_host"
}
