package com.faforever.commons.api.dto;

import com.github.jasminb.jsonapi.annotations.Relationship;
import com.github.jasminb.jsonapi.annotations.Type;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Type("modVersionReviewsSummary")
@Data
@EqualsAndHashCode(callSuper = true)
public class ModVersionReviewsSummary extends ReviewsSummary {
  @Relationship("modVersion")
  private ModVersion modVersion;
}
