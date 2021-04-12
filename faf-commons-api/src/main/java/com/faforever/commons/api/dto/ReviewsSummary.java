package com.faforever.commons.api.dto;

import com.github.jasminb.jsonapi.annotations.Id;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.SuperBuilder;

@Data
@SuperBuilder
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
