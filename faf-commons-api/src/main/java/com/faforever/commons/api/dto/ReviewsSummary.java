package com.faforever.commons.api.dto;

import com.github.jasminb.jsonapi.annotations.Id;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

@Data
@ToString(onlyExplicitlyIncluded = true)
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class ReviewsSummary {
  @Id
  @ToString.Include
  @EqualsAndHashCode.Include
  private String id;
  private float positive;
  private float negative;
  private float score;
  private int reviews;
  private float lowerBound;

}
