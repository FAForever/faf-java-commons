package com.faforever.commons.api.dto;

import com.github.jasminb.jsonapi.annotations.Id;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@Builder
@EqualsAndHashCode(of = "id")
public class ReviewsSummary {
  @Id
  String id;
  float positive;
  float negative;
  float score;
  int reviews;
  float lowerBound;

}
