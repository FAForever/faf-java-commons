package com.faforever.commons.api.dto;

import com.github.jasminb.jsonapi.annotations.Relationship;
import com.github.jasminb.jsonapi.annotations.Type;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

@Data
@ToString(callSuper = true)
@EqualsAndHashCode(callSuper = true)
@Type("mapPoolAssignment")
public class MapPoolAssignment extends AbstractEntity {

  int weight;
  private MapParams mapParams;

  @Relationship("mapPool")
  private MapPool mapPool;

  @Relationship("mapVersion")
  private MapVersion mapVersion;
}


