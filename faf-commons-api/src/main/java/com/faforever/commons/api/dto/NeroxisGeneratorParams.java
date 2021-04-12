package com.faforever.commons.api.dto;

import lombok.Value;
import lombok.experimental.SuperBuilder;

@Value
@SuperBuilder(toBuilder = true)
public class NeroxisGeneratorParams implements MapParams {
  int spawns;
  int size;
  String version;
}
