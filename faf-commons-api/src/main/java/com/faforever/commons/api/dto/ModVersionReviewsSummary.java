package com.faforever.commons.api.dto;

import com.github.jasminb.jsonapi.annotations.Relationship;
import com.github.jasminb.jsonapi.annotations.Type;
import lombok.Value;

@Type("modVersionReviewsSummary")
@Value
public class ModVersionReviewsSummary extends ReviewsSummary {

  @Relationship("modVersion")
  ModVersion modVersion;
}
