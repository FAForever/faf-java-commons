package com.faforever.commons.lobby

import com.fasterxml.jackson.annotation.JsonProperty
import com.fasterxml.jackson.annotation.JsonSubTypes
import com.fasterxml.jackson.annotation.JsonTypeInfo
import java.time.OffsetDateTime

@JsonTypeInfo(use = JsonTypeInfo.Id.NAME, include = JsonTypeInfo.As.EXISTING_PROPERTY, property = "command")
@JsonSubTypes(
  JsonSubTypes.Type(value = PingMessage::class, name = "ping"),
  JsonSubTypes.Type(value = LoginFailedResponse::class, name = "authentication_failed"),
  JsonSubTypes.Type(value = NoticeInfo::class, name = "notice"),
  JsonSubTypes.Type(value = UpdateInfo::class, name = "update"),
  JsonSubTypes.Type(value = InvalidResponse::class, name = "invalid"),
  JsonSubTypes.Type(value = SessionResponse::class, name = "session"),
  JsonSubTypes.Type(value = LoginSuccessResponse::class, name = "welcome"),
  JsonSubTypes.Type(value = PlayerInfo::class, name = "player_info"),
  JsonSubTypes.Type(value = SocialInfo::class, name = "social"),
  JsonSubTypes.Type(value = MatchmakerInfo::class, name = "matchmaker_info"),
  JsonSubTypes.Type(value = GameInfo::class, name = "game_info"),
  JsonSubTypes.Type(value = GameLaunchResponse::class, name = "game_launch"),
  JsonSubTypes.Type(value = MatchmakerMatchFoundResponse::class, name = "match_found"),
  JsonSubTypes.Type(value = MatchmakingInfo::class, name = "game_matchmaking"),
  JsonSubTypes.Type(value = SearchInfo::class, name = "search_info"),
  JsonSubTypes.Type(value = IceServerListResponse::class, name = "ice_servers"),
  JsonSubTypes.Type(value = PartyInfo::class, name = "update_party"),
  JsonSubTypes.Type(value = HostGameGpgCommand::class, name = "HostGame"),
  JsonSubTypes.Type(value = JoinGameGpgCommand::class, name = "JoinGame"),
  JsonSubTypes.Type(value = ConnectToPeerGpgCommand::class, name = "ConnectToPeer"),
  JsonSubTypes.Type(value = IceMsgGpgCommand::class, name = "IceMsg"),
  JsonSubTypes.Type(value = DisconnectFromPeerGpgCommand::class, name = "DisconnectFromPeer"),
)

interface ServerMessage : LobbyProtocolMessage

class PingMessage : ServerMessage

data class LoginFailedResponse(
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
  val clan: String?,
  val avatar: Avatar?,
  @JsonProperty("global_rating")
  val globalRating: List<Float>?,
  @JsonProperty("ladder_rating")
  val ladderRating: List<Float>?,
  @JsonProperty("number_of_games")
  val numberOfGames: Int,
  val country: String,
  val league: Map<String, String>?,
  val ratings: Map<String, LeaderboardRating>,
) {
  data class Avatar(
    val url: String,
    @JsonProperty("tooltip")
    val description: String,
  )

  data class LeaderboardRating(
    @JsonProperty("number_of_games")
    val numberOfGame: Int,
    val rating: List<Float>
  )
}

data class LoginSuccessResponse(
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

data class MatchmakerInfo(
  val queues: List<MatchmakerQueue>,
) : ServerMessage {
  data class MatchmakerQueue(
    @JsonProperty("queue_name")
    val name: String,
    @JsonProperty("queue_pop_time")
    val popTime: OffsetDateTime,
    @JsonProperty("team_size")
    val teamSize: Int,
    @JsonProperty("num_players")
    val numberOfPlayers: Int,
    @JsonProperty("boundary_75s")
    val boundary75s: List<List<Int>>,
    @JsonProperty("boundary_80s")
    val boundary80s: List<List<Int>>,
  )
}

data class MatchmakerMatchFoundResponse(
  @JsonProperty("queue_name")
  val queue: String,
) : ServerMessage

/**
 * GameInfo comes as single message or as a nested list
 * which makes all fields nullable. Thanks for nothing...
 */
data class GameInfo(
  val uid: Long?,
  val title: String?,
  val host: String?,
  @JsonProperty("game_type")
  val gameType: GameType?,
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
  @JsonProperty("rating_type")
  val leaderboard: String?,
  @JsonProperty("sim_mods")
  val simMods: Map<String, String>?,
  @JsonProperty("mapname")
  val mapName: String?,
  @JsonProperty("map_file_path")
  val mapFilePath: String?,
  @JsonProperty("launched_at")
  val launchedAt: Double?,
  val teams: Map<String, List<String>>?,
  @JsonProperty("rating_min")
  val ratingMin: Int?,
  @JsonProperty("rating_max")
  val ratingMax: Int?,
  @JsonProperty("enforce_rating_range")
  val enforceRatingRange: Boolean?,

  val games: List<GameInfo>?,
) : ServerMessage

data class GameLaunchResponse(
  val uid: Int,
  val name: String,
  @JsonProperty("mod")
  val featureMod: String,
  @JsonProperty("init_mode")
  val lobbyMode: LobbyMode,
  /**
   * Technical name of the leaderboard to select ratings to be shown
   */
  @JsonProperty("rating_type")
  val leaderboard: String,
  val args: List<Any>,

  @JsonProperty("mapname")
  val mapName: String? = null,
  val expectedPlayers: Int? = null,
  val mapPosition: Int? = null,
  val team: Int? = null,
  val faction: Faction? = null,
) : ServerMessage

data class MatchmakingInfo(
  val state: MatchmakerState,
) : ServerMessage

data class SearchInfo(
  @JsonProperty("queue_name")
  val queueName: String,
  val state: String,
) : ServerMessage


data class IceServer(
  val url: String?,
  val urls: Collection<String>?,
  val username: String,
  val credential: String,
  val credentialType: String,
)

data class IceServerListResponse(
  @JsonProperty("ice_servers")
  val iceServers: Collection<IceServer>,
  val ttl: Int,
) : ServerMessage {
}

data class PartyInfo(
  val owner: Int,
  val members: List<PartyMember>,
) : ServerMessage {
  data class PartyMember(
    @JsonProperty("player")
    val playerId: Int,
    val factions: List<Faction>
  )
}

interface GpgGameInboundMessage : ServerMessage {
  val target: String
  val args: List<Any>
}

data class HostGameGpgCommand(
  override val target: String,
  override val args: List<Any>,
) : GpgGameInboundMessage

data class JoinGameGpgCommand(
  override val target: String,
  override val args: List<Any>,
) : GpgGameInboundMessage

data class ConnectToPeerGpgCommand(
  override val target: String,
  override val args: List<Any>,
) : GpgGameInboundMessage

data class IceMsgGpgCommand(
  override val target: String,
  override val args: List<Any>,
) : GpgGameInboundMessage

data class DisconnectFromPeerGpgCommand(
  override val target: String,
  override val args: List<Any>,
) : GpgGameInboundMessage
