package com.faforever.commons.replay.body;

import com.faforever.commons.replay.body.parse.Event;

import java.util.List;

public record Body(List<Event> events) {
}
