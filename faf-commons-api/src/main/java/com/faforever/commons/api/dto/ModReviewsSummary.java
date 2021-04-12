package com.faforever.commons.api.dto;

import com.github.jasminb.jsonapi.annotations.Relationship;
import com.github.jasminb.jsonapi.annotations.Type;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Value;

@Type("modReviewsSummary")
@Value
@Builder
@EqualsAndHashCode(callSuper = true)
public class ModReviewsSummary extends ReviewsSummary {

  @Relationship("mod")
  Mod mod;
}
