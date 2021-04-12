package com.faforever.commons.api.dto;

import com.github.jasminb.jsonapi.annotations.Relationship;
import com.github.jasminb.jsonapi.annotations.Type;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Value;

@Value
@Builder
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


