package com.faforever.commons.replay.semantics;

import com.faforever.commons.replay.body.Event;
import com.faforever.commons.replay.header.Source;
import com.faforever.commons.replay.semantics.records.ChatMessage;
import com.faforever.commons.replay.semantics.records.TrackedEvent;
import com.faforever.commons.replay.shared.LuaTable;

import java.time.Duration;
import java.util.List;
import java.util.Objects;

public class Semantics {

  public static Duration tickToDuration(int tick) {
    return Duration.ofSeconds(tick / 10);
  }

  public static List<TrackedEvent> registerEvents(List<Source> sources, List<Event> events) {
    final int[] tick = {-1};
    final int[] clientId = {-1};

    return events.stream().map((event) -> switch (event) {
      case Event.Advance e -> {
        tick[0] = tick[0] + e.ticksToAdvance();
        yield null;
      }

      case Event.SetCommandSource e -> {
        clientId[0] = e.playerIndex();
        yield null;
      }

      default -> new TrackedEvent(tick[0], sources.get(clientId[0]), event);
    }).filter(Objects::nonNull).toList();
  }

  ;

  /**
   * Retrieves all events that are chat messages
   *
   * @param events A list of events
   * @return A list of events that are chat messages
   */
  public static List<ChatMessage> findChatMessages(List<Source> sources, List<TrackedEvent> events) {
    return events.stream().map((trackedEvent) -> switch (trackedEvent.event()) {

      // TODO: the fact that we piggy-back on the 'GiveResourcesToPlayer' callback to embed chat messages is all wrong! We should instead introduce an alternative callback with the sole purpose to send messages.
      //  Requires refactoring in the game!

      case Event.LuaSimCallback(
        String func, LuaTable parametersLua, Event.CommandUnits commandUnits
      ) when func.equals("GiveResourcesToPlayer") -> {
        yield switch (parametersLua) {
          case LuaTable.Table callbackTable -> {

            // TODO: this field has no meaning and can be manipulated, instead use the authorised command source.
            //  Requires refactoring in the game!
            if (!(callbackTable.value().get("From") instanceof LuaTable.Number from)) {
              yield null;
            }

            if (from.value() - 1 <= -2) {
              yield null;
            }

            // TODO: this field has no meaning and can be manipulated, instead use the authorised command source.
            //  Requires refactoring in the game!
            if (!(callbackTable.value().get("Sender") instanceof LuaTable.String sender)) {
              yield null;
            }

            // TODO: apparently all players create a sim callback that contains the chat message. This hack is how we skip it,
            //   Requires refactoring in the game!
            if (!Objects.equals(sender.value(), trackedEvent.source().name())) {
              yield null;
            }

            if (!(callbackTable.value().get("Msg") instanceof LuaTable.Table msgTable)) {
              yield null;
            }


            // TODO: this is 1 out of the 2 legitimate fields
            if (!(msgTable.value().get("to") instanceof LuaTable.String msgTo)) {
              yield null;
            }

            // TODO: this is 2 out of the 2 legitimate fields
            if (!(msgTable.value().get("text") instanceof LuaTable.String msgText)) {
              yield null;
            }

            yield new ChatMessage(tickToDuration(trackedEvent.tick()), trackedEvent.source().name(), msgTo.value(), msgText.value());
          }
          default -> null;
        };
      }
      default -> null;
    }).filter(Objects::nonNull).toList();
  }
}
