package com.faforever.commons.api.dto;

import com.github.jasminb.jsonapi.annotations.Relationship;
import com.github.jasminb.jsonapi.annotations.Type;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = true)
@Type("mapVersionReviewsSummary")
public class MapVersionReviewsSummary extends ReviewsSummary {
  @Relationship("MapVersion")
  private MapVersion mapVersion;
}
