package com.faforever.commons.api.dto;

import lombok.Builder;
import lombok.Value;

@Value
@Builder
public class NeroxisGeneratorParams implements MapParams {
  int spawns;
  int size;
  String version;
}
