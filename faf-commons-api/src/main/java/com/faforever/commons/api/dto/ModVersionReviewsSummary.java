package com.faforever.commons.api.dto;

import com.github.jasminb.jsonapi.annotations.Relationship;
import com.github.jasminb.jsonapi.annotations.Type;
import lombok.EqualsAndHashCode;
import lombok.Value;
import lombok.experimental.SuperBuilder;

@Type("modVersionReviewsSummary")
@Value
@SuperBuilder
@EqualsAndHashCode(callSuper = true)
public class ModVersionReviewsSummary extends ReviewsSummary {

  @Relationship("modVersion")
  ModVersion modVersion;
}
