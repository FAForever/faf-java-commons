package com.faforever.commons.api.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class NeroxisGeneratorParams implements MapParams {
  private int spawns;
  private int size;
  private String version;
}
