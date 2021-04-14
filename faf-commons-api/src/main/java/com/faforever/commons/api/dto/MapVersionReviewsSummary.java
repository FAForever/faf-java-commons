package com.faforever.commons.api.dto;

import com.github.jasminb.jsonapi.annotations.Relationship;
import com.github.jasminb.jsonapi.annotations.Type;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

@Data
@EqualsAndHashCode(callSuper = true)
@ToString(of = {"mapVersion"}, callSuper = true)
@Type("mapVersionReviewsSummary")
public class MapVersionReviewsSummary extends ReviewsSummary {
  @Relationship("MapVersion")
  private MapVersion mapVersion;
}
