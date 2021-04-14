package com.faforever.commons.api.dto;

import com.github.jasminb.jsonapi.annotations.Relationship;
import com.github.jasminb.jsonapi.annotations.Type;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

@Type("modVersionReviewsSummary")
@Data
@ToString(of = {"modVersion"}, callSuper = true)
@EqualsAndHashCode(callSuper = true)
public class ModVersionReviewsSummary extends ReviewsSummary {
  @Relationship("modVersion")
  private ModVersion modVersion;
}
