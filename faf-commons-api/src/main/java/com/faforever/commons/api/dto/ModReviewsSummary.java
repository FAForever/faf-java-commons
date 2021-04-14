package com.faforever.commons.api.dto;

import com.github.jasminb.jsonapi.annotations.Relationship;
import com.github.jasminb.jsonapi.annotations.Type;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Type("modReviewsSummary")
@Data
@EqualsAndHashCode(callSuper = true)
public class ModReviewsSummary extends ReviewsSummary {
  @Relationship("mod")
  private Mod mod;
}
