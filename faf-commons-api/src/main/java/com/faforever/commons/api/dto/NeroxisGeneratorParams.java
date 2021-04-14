package com.faforever.commons.api.dto;

import lombok.Data;
import lombok.ToString;

@Data
@ToString(callSuper = true)
public class NeroxisGeneratorParams implements MapParams {
  private int spawns;
  private int size;
  private String version;
}
