package com.faforever.commons.lobby

import com.fasterxml.jackson.databind.ObjectMapper
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule
import com.fasterxml.jackson.module.kotlin.KotlinModule
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test

class ClientMessageTest {

  companion object {
    val objectMapper: ObjectMapper = ObjectMapper()
      .registerModule(KotlinModule())
      .registerModule(JavaTimeModule())
  }

  @Test
  fun serializeHostGameRequest() {
    assertEquals("{\"command\":\"game_host\",\"mapname\":\"name\",\"title\":\"title\",\"mod\":\"mod\",\"options\":[],\"access\":\"public\",\"version\":1,\"password\":null,\"visibility\":\"public\",\"rating_min\":null,\"rating_max\":null,\"enforce_rating_range\":false}",
      objectMapper.writeValueAsString(
        HostGameRequest(
          "name",
          "title",
          "mod",
          BooleanArray(0),
          GameAccess.PUBLIC,
          1,
          null,
          GameVisibility.PUBLIC,
          null,
          null,
          false
        )
      )
    )
  }

  @Test
  fun serializeJoinGameRequest() {
    assertEquals("{\"command\":\"game_join\",\"uid\":0,\"password\":null}",
      objectMapper.writeValueAsString(JoinGameRequest(0, null)))
  }

  @Test
  fun serializeSessionRequest() {
    assertEquals("{\"command\":\"ask_session\",\"version\":\"1\",\"user_agent\":\"boo\"}",
      objectMapper.writeValueAsString(SessionRequest("1", "boo")))
  }

  @Test
  fun serializeAddFriendRequest() {
    assertEquals("{\"command\":\"social_add\",\"friend\":0}",
      objectMapper.writeValueAsString(AddFriendRequest(0)))
  }

  @Test
  fun serializeAddFoeRequest() {
    assertEquals("{\"command\":\"social_add\",\"foe\":0}",
      objectMapper.writeValueAsString(AddFoeRequest(0)))
  }

  @Test
  fun serializeRemoveFriendRequest() {
    assertEquals("{\"command\":\"social_remove\",\"friend\":0}",
      objectMapper.writeValueAsString(RemoveFriendRequest(0)))
  }

  @Test
  fun serializeRemoveFoeRequest() {
    assertEquals("{\"command\":\"social_remove\",\"foe\":0}",
      objectMapper.writeValueAsString(RemoveFoeRequest(0)))
  }

  @Test
  fun serializeAvatarListRequest() {
    assertEquals("{\"command\":\"avatar\",\"action\":\"list_avatar\"}",
      objectMapper.writeValueAsString(AvatarListRequest()))
  }

  @Test
  fun serializeSelectAvatarRequest() {
    assertEquals("{\"command\":\"avatar\",\"avatar\":\"boo\",\"action\":\"select\"}",
      objectMapper.writeValueAsString(SelectAvatarRequest("boo")))
  }

  @Test
  fun serializeIceServerListRequest() {
    assertEquals("{\"command\":\"ice_servers\"}",
      objectMapper.writeValueAsString(IceServerListRequest()))
  }

  @Test
  fun serializeRestoreGameSessionRequest() {
    assertEquals("{\"command\":\"restore_game_session\",\"game_id\":0}",
      objectMapper.writeValueAsString(RestoreGameSessionRequest(0)))
  }

  @Test
  fun serializePingMessage() {
    assertEquals("{\"command\":\"ping\"}",
      objectMapper.writeValueAsString(PingMessage()))
  }

  @Test
  fun serializePongMessage() {
    assertEquals("{\"command\":\"pong\"}",
      objectMapper.writeValueAsString(PongMessage()))
  }

  @Test
  fun serializeClosePlayerGameRequest() {
    assertEquals("{\"command\":\"admin\",\"user_id\":0,\"action\":\"closeFA\"}",
      objectMapper.writeValueAsString(ClosePlayerGameRequest(0)))
  }

  @Test
  fun serializeClosePlayerLobbyRequest() {
    assertEquals("{\"command\":\"admin\",\"user_id\":0,\"action\":\"closelobby\"}",
      objectMapper.writeValueAsString(ClosePlayerLobbyRequest(0)))
  }

  @Test
  fun serializeBroadcastRequest() {
    assertEquals("{\"command\":\"admin\",\"message\":\"boo\",\"action\":\"broadcast\"}",
      objectMapper.writeValueAsString(BroadcastRequest("boo")))
  }

  @Test
  fun serializeInviteToPartyRequest() {
    assertEquals("{\"command\":\"invite_to_party\",\"recipient_id\":0}",
      objectMapper.writeValueAsString(InviteToPartyRequest(0)))
  }

  @Test
  fun serializeAcceptInviteToPartyRequest() {
    assertEquals("{\"command\":\"accept_party_invite\",\"sender_id\":0}",
      objectMapper.writeValueAsString(AcceptInviteToPartyRequest(0)))
  }

  @Test
  fun serializeKickPlayerFromPartyRequest() {
    assertEquals("{\"command\":\"kick_player_from_party\",\"kicked_player_id\":0}",
      objectMapper.writeValueAsString(KickPlayerFromPartyRequest(0)))
  }

  @Test
  fun serializeLeavePartyRequest() {
    assertEquals("{\"command\":\"leave_party\"}",
      objectMapper.writeValueAsString(LeavePartyRequest()))
  }

  @Test
  fun serializeReadyPartyRequest() {
    assertEquals("{\"command\":\"ready_party\"}",
      objectMapper.writeValueAsString(ReadyPartyRequest()))
  }

  @Test
  fun serializeUnreadyPartyRequest() {
    assertEquals("{\"command\":\"unready_party\"}",
      objectMapper.writeValueAsString(UnreadyPartyRequest()))
  }

  @Test
  fun serializeSelectPartyFactionsRequest() {
    assertEquals("{\"command\":\"set_party_factions\",\"factions\":[\"aeon\",\"seraphim\",\"uef\",\"cybran\"]}",
      objectMapper.writeValueAsString(
        SelectPartyFactionsRequest(
          setOf(
            Faction.AEON,
            Faction.SERAPHIM,
            Faction.UEF,
            Faction.CYBRAN
          )
        )
      )
    )
  }

  @Test
  fun serializeGameMatchmakingRequest() {
    assertEquals("{\"command\":\"game_matchmaking\",\"queue_name\":\"1\",\"state\":\"start\"}",
      objectMapper.writeValueAsString(GameMatchmakingRequest("1", MatchmakerState.START)))
  }

  @Test
  fun serializeMatchmakerInfoRequest() {
    assertEquals("{\"command\":\"matchmaker_info\"}",
      objectMapper.writeValueAsString(MatchmakerInfoRequest()))
  }

  @Test
  fun serializeAuthenticateRequest() {
    assertEquals("{\"command\":\"auth\",\"token\":\"abc\",\"session\":1,\"unique_id\":\"def\"}",
      objectMapper.writeValueAsString(
        AuthenticateRequest(
          "abc",
          1,
          "def"
        )
      )
    )
  }
}