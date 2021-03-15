package com.faforever.commons.api.dto;

import com.github.jasminb.jsonapi.annotations.Relationship;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class MapPoolAssignment extends AbstractEntity {

  @Relationship("mapPool")
  private MapPool mapPool;

  @Relationship("mapVersion")
  private MapVersion mapVersion;

  private int weight;
  private String mapParams;
}
