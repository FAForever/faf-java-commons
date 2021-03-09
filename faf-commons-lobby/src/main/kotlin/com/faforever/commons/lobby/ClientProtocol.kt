package com.faforever.commons.lobby

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
  override val command = "hello"
}

data class SessionRequest(
  val version: String = "1.0",
  @JsonProperty("user_agent")
  val userAgent: String = "downlords-faf-client",
) : ClientMessage {
  override val command = "ask_session"
}

class IceServerListRequest : ClientMessage {
  override val command = "ice_servers"
}

data class BroadcastRequest(
  val message: String,
) : ClientMessage {
  override val command = "broadcast"
}

data class ClosePlayerGameRequest(
  @JsonProperty("user_id") val playerId: Int,
) : ClientMessage {
  override val command = "closeFA"
}

data class ClosePlayerLobbyRequest(
  @JsonProperty("user_id") val playerId: Int,
) : ClientMessage {
  override val command = "closelobby"
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
  override val command = "game_matchmaking"
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
  override val command = "game_host"
}

data class AddFriendRequest(
  @JsonProperty("friend") val playerId: Int
) : ClientMessage {
  override val command = "social_add"
}

data class AddFoeRequest(
  @JsonProperty("foe") val playerId: Int
) : ClientMessage {
  override val command = "social_add"
}

data class RemoveFriendRequest(
  @JsonProperty("friend") val playerId: Int
) : ClientMessage {
  override val command = "social_remove"
}

data class RemoveFoeRequest(
  @JsonProperty("foe") val playerId: Int
) : ClientMessage {
  override val command = "social_remove"
}

data class GpgGameMessage(
  override val command: String,
  val args: List<Any>,
) : ClientMessage

data class GameMatchmakingRequest(
  val queueName: String,
  val state: MatchmakerState,
) : ClientMessage {
  override val command = "game_matchmaking"
}

data class InviteToPartyRequest(
  @JsonProperty("recipientId")
  val playerId: Int
) : ClientMessage {
  override val command = "invite_to_party"
}

data class AcceptInviteToPartyRequest(
  @JsonProperty("senderId")
  val playerId: Int
) : ClientMessage {
  override val command = "accept_party_invite"
}

data class KickPlayerFromPartyRequest(
  @JsonProperty("kickedPlayerId")
  val playerId: Int
) : ClientMessage {
  override val command = "kick_player_from_party"
}

class ReadyPartyRequest(isReady: Boolean) : ClientMessage {
  override val command = if (isReady) "ready_party" else "unready_party"
}

class LeavePartyRequest : ClientMessage {
  override val command = "leave_party"
}

data class SelectPartyFactionsRequest(
  val factions: Set<Faction>
) : ClientMessage {
  override val command = "set_party_factions"
}
