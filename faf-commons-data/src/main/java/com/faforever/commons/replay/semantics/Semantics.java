package com.faforever.commons.replay.semantics;

import com.faforever.commons.replay.body.Event;
import com.faforever.commons.replay.semantics.records.TrackedEvent;

import java.util.List;
import java.util.Objects;

public class Semantics {

  public static List<TrackedEvent> registerEvents(List<Event> events) {
    final int[] tick = {-1};
    final int[] clientId = {-1};

    return events.stream().map((event) -> switch(event) {
      case Event.Advance e -> {
          tick[0] = tick[0] + e.ticksToAdvance();
          yield null;
      }

      case Event.SetCommandSource e -> {
        clientId[0] = e.playerIndex();
        yield null;
      }

      default -> new TrackedEvent(tick[0], clientId[0], event);
    }).filter(Objects::nonNull).toList();
  };
}
