package com.faforever.commons.lobby

import reactor.core.publisher.Mono


interface FafAdminLobbyClient {

  fun broadcastMessage(message: String)

  fun closePlayerGame(playerId: Int)

  fun closePlayerLobby(playerId: Int)

}

interface FafSocialLobbyClient {

  fun addFriend(playerId: Int)

  fun addFoe(playerId: Int)

  fun removeFriend(playerId: Int)

  fun removeFoe(playerId: Int)

}

interface FafMatchmakerLobbyClient {

  fun requestMatchmakerInfo()

  fun gameMatchmaking(queueName: String, state: MatchmakerState)

  fun inviteToParty(playerId: Int)

  fun acceptPartyInvite(playerId: Int)

  fun kickPlayerFromParty(playerId: Int)

  fun readyParty(isReady: Boolean)

  fun leaveParty()

  fun setPartyFactions(factions: Set<Faction>)
}
