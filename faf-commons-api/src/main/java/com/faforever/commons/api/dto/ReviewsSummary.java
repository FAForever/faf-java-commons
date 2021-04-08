package com.faforever.commons.api.dto;

import com.github.jasminb.jsonapi.annotations.Id;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(of = "id")
public class ReviewsSummary {
  @Id
  private String id;
  private float positive;
  private float negative;
  private float score;
  private int reviews;
  private float lowerBound;

}
