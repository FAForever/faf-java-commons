package com.faforever.commons.api.dto;

import com.github.jasminb.jsonapi.annotations.Relationship;
import com.github.jasminb.jsonapi.annotations.Type;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

@Data
@ToString(of = {"modVersion"}, callSuper = true)
@EqualsAndHashCode(callSuper = true)
@Type("modVersionReview")
public class ModVersionReview extends Review {
  @Relationship("modVersion")
  private ModVersion modVersion;
}
