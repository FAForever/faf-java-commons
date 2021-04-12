package com.faforever.commons.api.dto;

import com.github.jasminb.jsonapi.annotations.Relationship;
import com.github.jasminb.jsonapi.annotations.Type;
import lombok.Data;

@Type("modVersionReviewsSummary")
@Data
public class ModVersionReviewsSummary extends ReviewsSummary {

  @Relationship("modVersion")
  ModVersion modVersion;
}
