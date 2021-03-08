package com.faforever.commons.lobby

import com.faforever.loadtest.remote.Faction
import com.faforever.loadtest.remote.GameStatus
import com.faforever.loadtest.remote.LobbyMode
import com.faforever.loadtest.remote.LobbyProtocolMessage
import com.fasterxml.jackson.annotation.JsonProperty
import com.fasterxml.jackson.annotation.JsonSubTypes
import com.fasterxml.jackson.annotation.JsonTypeInfo
import java.time.OffsetDateTime

@JsonTypeInfo(use = JsonTypeInfo.Id.NAME, include = JsonTypeInfo.As.EXISTING_PROPERTY, property = "command")
@JsonSubTypes(
  JsonSubTypes.Type(value = PingMessage::class, name = "ping"),
  JsonSubTypes.Type(value = AuthenticationFailedResponse::class, name = "authentication_failed"),
  JsonSubTypes.Type(value = NoticeInfo::class, name = "notice"),
  JsonSubTypes.Type(value = UpdateInfo::class, name = "update"),
  JsonSubTypes.Type(value = InvalidResponse::class, name = "invalid"),
  JsonSubTypes.Type(value = SessionResponse::class, name = "session"),
  JsonSubTypes.Type(value = LoginResponse::class, name = "welcome"),
  JsonSubTypes.Type(value = PlayerInfo::class, name = "player_info"),
  JsonSubTypes.Type(value = SocialInfo::class, name = "social"),
  JsonSubTypes.Type(value = MatchmakerInfo::class, name = "matchmaker_info"),
  JsonSubTypes.Type(value = GameInfo::class, name = "game_info"),
  JsonSubTypes.Type(value = GameLaunchResponse::class, name = "game_launch"),
  JsonSubTypes.Type(value = MatchmakerMatchFoundResponse::class, name = "match_found"),
  JsonSubTypes.Type(value = MatchmakingInfo::class, name = "game_matchmaking"),
  JsonSubTypes.Type(value = SearchInfo::class, name = "search_info"),
)

interface ServerMessage : LobbyProtocolMessage

class PingMessage : ServerMessage

data class AuthenticationFailedResponse(
  val text: String?,
) : ServerMessage

data class NoticeInfo(
  val style: String?,
  val text: String?,
) : ServerMessage

data class UpdateInfo(
  val update: String?,
  @JsonProperty("new_version")
  val newVersion: String?,
) : ServerMessage

class InvalidResponse : ServerMessage

data class SessionResponse(
  val session: Long,
) : ServerMessage

data class Player(
  val id: Int,
  val login: String,
  @JsonProperty("global_rating")
  val globalRating: List<Float>?,
  @JsonProperty("ladder_rating")
  val ladderRating: List<Float>?,
  @JsonProperty("number_of_games")
  val numberOfGames: Int,
  val country: String,
)

data class LoginResponse(
  val id: Int,
  val login: String,
  val me: Player,
) : ServerMessage

data class PlayerInfo(
  val players: List<Player>,
) : ServerMessage

data class SocialInfo(
  val autojoin: List<String>,
  val channels: List<String>,
  val friends: List<Int>,
  val foes: List<Int>,
  val power: Int,
) : ServerMessage

data class MatchmakerQueue(
  @JsonProperty("queue_name")
  val name: String,
  @JsonProperty("queue_pop_time")
  val popTime: OffsetDateTime
)

data class MatchmakerInfo(
  val queues: List<MatchmakerQueue>,
) : ServerMessage

data class MatchmakerMatchFoundResponse(
  val queue: String,
) : ServerMessage

/**
 * GameInfo comes as single message or as a nested list
 */
data class GameInfo(
  val uid: Long?,
  val title: String?,
  val host: String?,
  @JsonProperty("max_players")
  val maxPlayers: Int?,
  @JsonProperty("num_players")
  val numberOfPlayers: Int?,
  val visibility: String?,
  @JsonProperty("password_protected")
  val passwordProtected: Boolean?,
  val state: GameStatus?,
  @JsonProperty("featured_mod")
  val featuredMod: String?,
  @JsonProperty("sim_mods")
  val simMods: Map<String, String>?,
  @JsonProperty("mapname")
  val mapName: String?,
  @JsonProperty("map_file_path")
  val mapFilePath: String?,
  @JsonProperty("launched_at")
  val launchedAt: Double?,
  val teams: Map<String, List<String>>?,

  val games: List<GameInfo>?,
) : ServerMessage

data class GameLaunchResponse(
  val uid: Int,
  val name: String,
  val mod: String,
  @JsonProperty("init_mode")
  val lobbyMode: Int,
  val args: List<Any>,
) : ServerMessage

data class GameLaunchRequest(
  val uid: Int,
  val name: String,
  val mapName: String,
  val mod: String,
  val expectedPlayers: Int,
  val mapPosition: Int,
  val faction: Faction,
  val initMode: LobbyMode,
  val args: List<String>,
) : ServerMessage

data class MatchmakingInfo(
  val state: MatchmakerState,
) : ServerMessage

data class SearchInfo(
  @JsonProperty("queue_name")
  val queueName: String,
  val state: String,
) : ServerMessage
