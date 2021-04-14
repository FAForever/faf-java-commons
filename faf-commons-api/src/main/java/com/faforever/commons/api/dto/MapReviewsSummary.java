package com.faforever.commons.api.dto;

import com.github.jasminb.jsonapi.annotations.Relationship;
import com.github.jasminb.jsonapi.annotations.Type;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = true)
@Type("mapReviewsSummary")
public class MapReviewsSummary extends ReviewsSummary {

  @Relationship("Map")
  private Map map;
}
