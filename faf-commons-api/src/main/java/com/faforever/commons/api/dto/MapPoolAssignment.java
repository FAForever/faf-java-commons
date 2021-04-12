package com.faforever.commons.api.dto;

import com.github.jasminb.jsonapi.annotations.Relationship;
import com.github.jasminb.jsonapi.annotations.Type;
import lombok.EqualsAndHashCode;
import lombok.Value;
import lombok.experimental.SuperBuilder;

@Value
@SuperBuilder
@EqualsAndHashCode(callSuper = true)
@Type("mapPoolAssignment")
public class MapPoolAssignment extends AbstractEntity {

  int weight;
  MapParams mapParams;

  @Relationship("mapPool")
  MapPool mapPool;

  @Relationship("mapVersion")
  MapVersion mapVersion;
}


