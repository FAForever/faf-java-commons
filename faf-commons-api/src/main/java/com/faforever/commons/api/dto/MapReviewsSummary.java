package com.faforever.commons.api.dto;

import com.github.jasminb.jsonapi.annotations.Relationship;
import com.github.jasminb.jsonapi.annotations.Type;
import lombok.Data;

@Data
@Type("mapReviewsSummary")
public class MapReviewsSummary extends ReviewsSummary {

  @Relationship("Map")
  private Map map;

}
