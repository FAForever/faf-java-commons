package com.faforever.commons.api.dto;

import com.github.jasminb.jsonapi.annotations.Relationship;
import com.github.jasminb.jsonapi.annotations.Type;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Value;

@Value
@Builder
@EqualsAndHashCode(callSuper = true)
@Type("mapVersionReviewsSummary")
public class MapVersionReviewsSummary extends ReviewsSummary {

  @Relationship("MapVersion")
  MapVersion mapVersion;

}
