package com.faforever.commons.api.dto;

import com.github.jasminb.jsonapi.annotations.Relationship;
import com.github.jasminb.jsonapi.annotations.Type;
import lombok.Data;

@Type("modReviewsSummary")
@Data
public class ModReviewsSummary extends ReviewsSummary {

  @Relationship("mod")
  Mod mod;
}
