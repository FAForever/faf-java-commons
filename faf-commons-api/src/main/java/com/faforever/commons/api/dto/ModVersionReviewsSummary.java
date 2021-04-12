package com.faforever.commons.api.dto;

import com.github.jasminb.jsonapi.annotations.Relationship;
import com.github.jasminb.jsonapi.annotations.Type;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Value;

@Type("modVersionReviewsSummary")
@Value
@Builder
@EqualsAndHashCode(callSuper = true)
public class ModVersionReviewsSummary extends ReviewsSummary {

  @Relationship("modVersion")
  ModVersion modVersion;
}
