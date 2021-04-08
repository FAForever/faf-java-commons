package com.faforever.commons.api.dto;

import com.github.jasminb.jsonapi.annotations.Relationship;
import com.github.jasminb.jsonapi.annotations.Type;
import lombok.Value;

@Type("modReviewsSummary")
@Value
public class ModReviewsSummary extends ReviewsSummary {

  @Relationship("mod")
  Mod mod;
}
