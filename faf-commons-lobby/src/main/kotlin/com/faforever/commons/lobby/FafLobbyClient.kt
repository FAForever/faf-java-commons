package com.faforever.commons.lobby

import com.fasterxml.jackson.databind.ObjectMapper
import io.netty.buffer.Unpooled
import io.netty.handler.codec.LineBasedFrameDecoder
import io.netty.handler.codec.string.LineEncoder
import io.netty.handler.codec.string.LineSeparator
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import reactor.core.Disposable
import reactor.core.publisher.Mono
import reactor.core.publisher.Sinks
import reactor.netty.Connection
import reactor.netty.tcp.TcpClient
import java.util.function.Function

class FafLobbyClient(
  private val config: Config,
  private val mapper: ObjectMapper,
) {
  companion object {
    val log: Logger = LoggerFactory.getLogger(FafLobbyClient::class.java)
  }

  data class Config(
    val host: String,
    val port: Int,
    val username: String,
    val password: String,
    val localIp: String,
    val generateUid: Function<Long, String>,
    val wiretap: Boolean = false,
  )

  private val eventSink: Sinks.Many<ServerMessage> = Sinks.many().multicast().directBestEffort()
  val events = eventSink.asFlux()

  private val outboundSink: Sinks.Many<String> = Sinks.many().unicast().onBackpressureBuffer()
  private val connectionMono: Mono<out Connection> = TcpClient.create(
  )
    .wiretap(config.wiretap)
    .host(config.host)
    .port(config.port)
    .doOnConnected { connection ->
      connection
        .addHandler(LineEncoder(LineSeparator.UNIX)) // TODO: This is not working. Raise a bug ticket! Workaround below
        .addHandler(LineBasedFrameDecoder(8192))
    }
    .handle { inbound, outbound ->
      val inboundMono = inbound.receive()
        .asString(Charsets.UTF_8)
        .doOnNext { log.debug("Inbound message: {}", it) }
        .map { mapper.readValue(it, ServerMessage::class.java) }
        .flatMap { handle(it) }
        .doOnError { log.error("Error during read", it) }
        .doOnComplete { log.info("Inbound channel closed") }
        .doFinally { log.info("Inbound channel finally") }
        .then()

      val outboundMono = outbound.send(
        outboundSink.asFlux()
          .doOnNext { log.debug("Outbound message: {}", it) }
          .doOnError { log.error("Error during read", it) }
          .doOnComplete { log.error("Outbound channel closed") }
          .doFinally { log.info("Outbound channel finally") }
          // appending line ending is workaround due to broken encoder
          .map { message -> Unpooled.copiedBuffer(message + "\n", Charsets.UTF_8) }
      ).then()

      inboundMono.mergeWith(outboundMono)
    }
    .connect()

  private var connectionSubscription: Disposable? = null

  fun connectAndLogin() =
    Mono.fromCallable {
      connectionSubscription = connectionMono.subscribe()
      send(SessionRequest())
    }.then(
      events
        .filter { it is LoginResponse }
        .next()
    )

  fun disconnect() {
    when (val subscription = connectionSubscription) {
      null -> log.warn("Attempting to disconnect while never connected")
      else -> subscription.dispose().also { log.info("Disconnecting") }
    }
  }

  fun send(message: ClientMessage) {
    outboundSink.tryEmitNext(mapper.writeValueAsString(message))
  }

  private fun handle(message: ServerMessage): Mono<Unit> = when (message) {
    is SessionResponse -> Mono.fromCallable {
      send(
        LoginRequest(
          config.username,
          config.password,
          message.session,
          config.localIp,
          config.generateUid.apply(message.session),
        )
      )
      eventSink.tryEmitNext(message)
      Unit
    }
    else -> Mono.fromCallable {
      eventSink.tryEmitNext(message)
      Unit
    }
  }
}
