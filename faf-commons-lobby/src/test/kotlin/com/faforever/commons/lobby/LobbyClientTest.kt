package com.faforever.commons.lobby

import com.fasterxml.jackson.databind.DeserializationFeature
import com.fasterxml.jackson.databind.ObjectMapper
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule
import com.fasterxml.jackson.module.kotlin.KotlinModule
import io.netty.handler.codec.LineBasedFrameDecoder
import io.netty.handler.codec.string.LineEncoder
import io.netty.handler.codec.string.LineSeparator
import org.junit.jupiter.api.AfterEach
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import reactor.core.publisher.Mono
import reactor.core.publisher.Sinks
import reactor.netty.Connection
import reactor.netty.DisposableServer
import reactor.netty.NettyInbound
import reactor.netty.NettyOutbound
import reactor.netty.tcp.TcpServer
import reactor.test.StepVerifier
import java.net.InetAddress
import java.nio.charset.StandardCharsets
import java.time.Duration
import java.time.temporal.ChronoUnit

class LobbyClientTest {
  companion object {
    val TIMEOUT: Long = 5000;
    val TIMEOUT_UNIT = ChronoUnit.MILLIS
    val LOOPBACK_ADDRESS = InetAddress.getLoopbackAddress()
    val LOG: Logger = LoggerFactory.getLogger(FafLobbyClient::class.java)
  }

  private val objectMapper: ObjectMapper = ObjectMapper().registerModule(KotlinModule())
    .registerModule(JavaTimeModule())
    .disable(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES)
    .enable(DeserializationFeature.READ_UNKNOWN_ENUM_VALUES_USING_DEFAULT_VALUE);
  private val token = "abc"
  private val serverReceivedSink = Sinks.many().unicast().onBackpressureBuffer<String>()
  private val serverMessagesReceived = serverReceivedSink.asFlux().publish().autoConnect()
  private val serverSentSink = Sinks.many().unicast().onBackpressureBuffer<String>()
  private lateinit var disposableServer: DisposableServer
  private val instance: FafLobbyClient = FafLobbyClient(objectMapper)
  private val playerUid = 123
  private val sessionId: Long = 456
  private val verificationDuration = Duration.of(TIMEOUT, TIMEOUT_UNIT)

  @BeforeEach
  fun setUp() {
    instance.minPingIntervalSeconds = Int.MAX_VALUE.toLong()
    startFakeFafLobbyServer()
  }

  private fun startFakeFafLobbyServer() {
    disposableServer = TcpServer.create()
      .doOnConnection { connection: Connection ->
        LOG.debug("New Client connected to server")
        connection.addHandler(LineEncoder(LineSeparator.UNIX)) // TODO: This is not working. Raise a bug ticket! Workaround below
          .addHandler(LineBasedFrameDecoder(1024 * 1024))
      }.doOnBound { disposableServer: DisposableServer ->
        LOG.debug(
          "Fake server listening at {} on port {}",
          disposableServer.host(),
          disposableServer.port()
        )
      }.noSSL()
      .host(LOOPBACK_ADDRESS.hostAddress)
      .handle { inbound: NettyInbound, outbound: NettyOutbound ->
        val inboundMono = inbound.receive()
          .asString(StandardCharsets.UTF_8)
          .doOnNext { message: String? ->
            LOG.debug("Received message at server {}", message)
            LOG.debug("Emit Result is {}", serverReceivedSink.tryEmitNext(message!!))
          }
          .then()
        val outboundMono = outbound.sendString(
          serverSentSink.asFlux()
            .doOnNext { LOG.debug("Sending message from fake server {}", it) }
            .map { message: String -> message.plus("\n")
            }, StandardCharsets.UTF_8
        ).then()
        inboundMono.mergeWith(outboundMono)
      }
      .bindNow()
  }

  private fun commandMatches(message: String, command: String) = message.contains(
    "\"command\":\"$command\""
  )

  private fun connectAndLogIn() {
    val config = FafLobbyClient.Config(
      Mono.just(token),
      "0",
      "downlords-faf-client",
      disposableServer.host(),
      disposableServer.port(),
      { "abc" },
      1024 * 1024,
      false,
      1,
      5,
      5
    )

    serverMessagesReceived.filter { commandMatches(it, "ask_session") }
      .next()
      .doOnNext {
        val sessionMessage = SessionResponse(sessionId)
        sendFromServer(sessionMessage)
      }.subscribe()

    serverMessagesReceived.filter { commandMatches(it, "auth") }
      .next()
      .doOnNext {
        val me = Player(playerUid, "Junit", null, null, "", HashMap(), HashMap())
        val loginServerMessage = LoginSuccessResponse(me)
        sendFromServer(loginServerMessage)
      }.subscribe()

    val stepVerifier = StepVerifier.create(serverMessagesReceived.take(2))
      .expectNextMatches { commandMatches(it, "ask_session") }
      .expectNextMatches { commandMatches(it, "auth") }
      .expectComplete()
      .verifyLater()

    StepVerifier.create(instance.connectAndLogin(config)).expectNextCount(1).verifyComplete()

    stepVerifier.verify(verificationDuration)
  }

  private fun sendFromServer(fafServerMessage: ServerMessage) {
    serverSentSink.tryEmitNext(objectMapper.writeValueAsString(fafServerMessage))
  }

  @AfterEach
  fun tearDown() {
    disposableServer.disposeNow()
    instance.disconnect()
  }

  @Test
  fun testBroadcast() {
    connectAndLogIn()

    val stepVerifier = StepVerifier.create(serverMessagesReceived.take(1))
      .expectNextMatches { commandMatches(it, "admin") }
      .expectComplete()
      .verifyLater()

    instance.broadcastMessage("test")

    stepVerifier.verify(verificationDuration)
  }

  @Test
  fun testClosePlayerGame() {
    connectAndLogIn()

    val stepVerifier = StepVerifier.create(serverMessagesReceived.take(1))
      .expectNextMatches { commandMatches(it, "admin") }
      .expectComplete()
      .verifyLater()

    instance.closePlayerGame(0)

    stepVerifier.verify(verificationDuration)
  }

  @Test
  fun testClosePlayerLobby() {
    connectAndLogIn()

    val stepVerifier = StepVerifier.create(serverMessagesReceived.take(1))
      .expectNextMatches { commandMatches(it, "admin") }
      .expectComplete()
      .verifyLater()

    instance.closePlayerLobby(0)

    stepVerifier.verify(verificationDuration)
  }

  @Test
  fun testHostGame() {
    connectAndLogIn()

    val stepVerifier = StepVerifier.create(serverMessagesReceived.take(1))
      .expectNextMatches { commandMatches(it, "game_host") }
      .expectComplete()
      .verifyLater()

    instance.requestHostGame(
      "blah",
      "map",
      "faf",
      GameVisibility.PUBLIC,
      null,
      null,
      null,
      false,
    ).subscribe()

    stepVerifier.verify(verificationDuration)
  }

  @Test
  fun testJoinGame() {
    connectAndLogIn()

    val stepVerifier = StepVerifier.create(serverMessagesReceived.take(1))
      .expectNextMatches { commandMatches(it, "game_join") }
      .expectComplete()
      .verifyLater()

    instance.requestJoinGame(0, null).subscribe()

    stepVerifier.verify(verificationDuration)
  }

  @Test
  fun testRestoreGameSession() {
    connectAndLogIn()

    val stepVerifier = StepVerifier.create(serverMessagesReceived.take(1))
      .expectNextMatches { commandMatches(it, "restore_game_session") }
      .expectComplete()
      .verifyLater()

    instance.restoreGameSession(0)

    stepVerifier.verify(verificationDuration)
  }

  @Test
  fun testGetIceServers() {
    connectAndLogIn()

    val stepVerifier = StepVerifier.create(serverMessagesReceived.take(1))
      .expectNextMatches { commandMatches(it, "ice_servers") }
      .expectComplete()
      .verifyLater()

    instance.getIceServers().subscribe()

    stepVerifier.verify(verificationDuration)
  }

  @Test
  fun testAddFriend() {
    connectAndLogIn()

    val stepVerifier = StepVerifier.create(serverMessagesReceived.take(1))
      .expectNextMatches { commandMatches(it, "social_add") }
      .expectComplete()
      .verifyLater()

    instance.addFriend(0)

    stepVerifier.verify(verificationDuration)
  }

  @Test
  fun testAddFoe() {
    connectAndLogIn()

    val stepVerifier = StepVerifier.create(serverMessagesReceived.take(1))
      .expectNextMatches { commandMatches(it, "social_add") }
      .expectComplete()
      .verifyLater()

    instance.addFoe(0)

    stepVerifier.verify(verificationDuration)
  }

  @Test
  fun testRemoveFriend() {
    connectAndLogIn()

    val stepVerifier = StepVerifier.create(serverMessagesReceived.take(1))
      .expectNextMatches { commandMatches(it, "social_remove") }
      .expectComplete()
      .verifyLater()

    instance.removeFriend(0)

    stepVerifier.verify(verificationDuration)
  }

  @Test
  fun testRemoveFoe() {
    connectAndLogIn()

    val stepVerifier = StepVerifier.create(serverMessagesReceived.take(1))
      .expectNextMatches { commandMatches(it, "social_remove") }
      .expectComplete()
      .verifyLater()

    instance.removeFoe(0)

    stepVerifier.verify(verificationDuration)
  }

  @Test
  fun testSelectAvatar() {
    connectAndLogIn()

    val stepVerifier = StepVerifier.create(serverMessagesReceived.take(1))
      .expectNextMatches { commandMatches(it, "avatar") }
      .expectComplete()
      .verifyLater()

    instance.selectAvatar(null)

    stepVerifier.verify(verificationDuration)
  }

  @Test
  fun testGetAvailableAvatars() {
    connectAndLogIn()

    val stepVerifier = StepVerifier.create(serverMessagesReceived.take(1))
      .expectNextMatches { commandMatches(it, "avatar") }
      .expectComplete()
      .verifyLater()

    instance.getAvailableAvatars().subscribe()

    stepVerifier.verify(verificationDuration)
  }

  @Test
  fun testRequestMatchmakerInfo() {
    connectAndLogIn()

    val stepVerifier = StepVerifier.create(serverMessagesReceived.take(1))
      .expectNextMatches { commandMatches(it, "matchmaker_info") }
      .expectComplete()
      .verifyLater()

    instance.requestMatchmakerInfo()

    stepVerifier.verify(verificationDuration)
  }

  @Test
  fun testGameMatchmaking() {
    connectAndLogIn()

    val stepVerifier = StepVerifier.create(serverMessagesReceived.take(1))
      .expectNextMatches { commandMatches(it, "game_matchmaking") }
      .expectComplete()
      .verifyLater()

    instance.gameMatchmaking("test", MatchmakerState.START)

    stepVerifier.verify(verificationDuration)
  }

  @Test
  fun testInviteToParty() {
    connectAndLogIn()

    val stepVerifier = StepVerifier.create(serverMessagesReceived.take(1))
      .expectNextMatches { commandMatches(it, "invite_to_party") }
      .expectComplete()
      .verifyLater()

    instance.inviteToParty(0)

    stepVerifier.verify(verificationDuration)
  }

  @Test
  fun testAcceptInvite() {
    connectAndLogIn()

    val stepVerifier = StepVerifier.create(serverMessagesReceived.take(1))
      .expectNextMatches { commandMatches(it, "accept_party_invite") }
      .expectComplete()
      .verifyLater()

    instance.acceptPartyInvite(0)

    stepVerifier.verify(verificationDuration)
  }

  @Test
  fun testKickPlayer() {
    connectAndLogIn()

    val stepVerifier = StepVerifier.create(serverMessagesReceived.take(1))
      .expectNextMatches { commandMatches(it, "kick_player_from_party") }
      .expectComplete()
      .verifyLater()

    instance.kickPlayerFromParty(0)

    stepVerifier.verify(verificationDuration)
  }

  @Test
  fun testReadyParty() {
    connectAndLogIn()

    val stepVerifier = StepVerifier.create(serverMessagesReceived.take(1))
      .expectNextMatches { commandMatches(it, "ready_party") }
      .expectComplete()
      .verifyLater()

    instance.readyParty()

    stepVerifier.verify(verificationDuration)
  }

  @Test
  fun testUnreadyParty() {
    connectAndLogIn()

    val stepVerifier = StepVerifier.create(serverMessagesReceived.take(1))
      .expectNextMatches { commandMatches(it, "unready_party") }
      .expectComplete()
      .verifyLater()

    instance.unreadyParty()

    stepVerifier.verify(verificationDuration)
  }

  @Test
  fun testLeaveParty() {
    connectAndLogIn()

    val stepVerifier = StepVerifier.create(serverMessagesReceived.take(1))
      .expectNextMatches { commandMatches(it, "leave_party") }
      .expectComplete()
      .verifyLater()

    instance.leaveParty()

    stepVerifier.verify(verificationDuration)
  }

  @Test
  fun testSetPartyFactions() {
    connectAndLogIn()

    val stepVerifier = StepVerifier.create(serverMessagesReceived.take(1))
      .expectNextMatches { commandMatches(it, "set_party_factions") }
      .expectComplete()
      .verifyLater()

    instance.setPartyFactions(setOf())

    stepVerifier.verify(verificationDuration)
  }

  @Test
  fun testSendGpgGameMessage() {
    connectAndLogIn()

    val stepVerifier = StepVerifier.create(serverMessagesReceived.take(1))
      .expectNextMatches { commandMatches(it, "Test") }
      .expectComplete()
      .verifyLater()

    instance.sendGpgGameMessage(GpgGameOutboundMessage("Test", listOf()))

    stepVerifier.verify(verificationDuration)
  }

  @Test
  fun testPingInterval() {
    connectAndLogIn()

    val stepVerifier = StepVerifier.create(serverMessagesReceived.take(1))
      .expectNextMatches { commandMatches(it, "ping") }
      .expectComplete()
      .verifyLater()

    instance.minPingIntervalSeconds = 1

    sendFromServer(ServerPongMessage())

    stepVerifier.verify(verificationDuration)
  }

  @Test
  fun testPingOnceInterval() {
    connectAndLogIn()

    val stepVerifier = StepVerifier.create(serverMessagesReceived.take(Duration.ofSeconds(2)))
      .expectNextMatches { commandMatches(it, "ping") }
      .expectComplete()
      .verifyLater()

    instance.minPingIntervalSeconds = 1

    sendFromServer(ServerPongMessage())
    sendFromServer(ServerPongMessage())

    stepVerifier.verify(verificationDuration)
  }

  @Test
  fun testPongInterval() {
    connectAndLogIn()

    val stepVerifier = StepVerifier.create(serverMessagesReceived.take(1))
      .expectNextMatches { commandMatches(it, "ping") }
      .expectComplete()
      .verifyLater()

    instance.minPingIntervalSeconds = 1

    sendFromServer(ServerPongMessage())

    stepVerifier.verify(verificationDuration)
  }

  @Test
  fun testPongIntervalFailure() {
    connectAndLogIn()

    val stepVerifier = StepVerifier.create(serverMessagesReceived.take(1))
      .expectNextMatches { commandMatches(it, "ping") }
      .expectComplete()
      .verifyLater()

    instance.minPingIntervalSeconds = 1

    sendFromServer(ServerPongMessage())

    instance.connectionStatus.blockFirst(Duration.ofSeconds(10))

    stepVerifier.verify(verificationDuration)
  }

  @Test
  fun testPingResponse() {
    connectAndLogIn()

    val stepVerifier = StepVerifier.create(serverMessagesReceived.take(1))
      .expectNextMatches { commandMatches(it, "pong") }
      .expectComplete()
      .verifyLater()

    sendFromServer(ServerPingMessage())

    stepVerifier.verify(verificationDuration)
  }

  @Test
  fun testOnAuthenticationFailed() {
    val stepVerifierServer = StepVerifier.create(serverMessagesReceived.take(2))
      .expectNextMatches { commandMatches(it, "ask_session") }
      .expectNextMatches { commandMatches(it, "auth") }
      .expectComplete()
      .verifyLater()

    val config = FafLobbyClient.Config(
      Mono.just(token),
      "0",
      "downlords-faf-client",
      disposableServer.host(),
      disposableServer.port(),
      { "abc" },
      1024 * 1024,
      false,
      1,
      5,
      5
    )

    serverMessagesReceived.filter { commandMatches(it, "ask_session") }
      .next()
      .doOnNext {
        val sessionMessage = SessionResponse(sessionId)
        sendFromServer(sessionMessage)
      }.subscribe()

    serverMessagesReceived.filter { commandMatches(it, "auth") }
      .next()
      .doOnNext {
        val authenticationFailedMessage = LoginFailedResponse("boo")
        sendFromServer(authenticationFailedMessage)
      }.subscribe()

    StepVerifier.create(instance.connectAndLogin(config))
      .expectError(LoginException::class.java)
      .verify(verificationDuration)

    stepVerifierServer.verify(verificationDuration)
  }
}
