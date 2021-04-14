package com.faforever.commons.api.dto;

import com.github.jasminb.jsonapi.annotations.Relationship;
import com.github.jasminb.jsonapi.annotations.Type;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

@Data
@Type("mapVersionReview")
@ToString(of = {"mapVersion"}, callSuper = true)
@EqualsAndHashCode(callSuper = true)
public class MapVersionReview extends Review {
  @Relationship("mapVersion")
  private MapVersion mapVersion;
}
