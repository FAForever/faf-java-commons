package com.faforever.commons.api.dto;

import com.github.jasminb.jsonapi.annotations.Relationship;
import com.github.jasminb.jsonapi.annotations.Type;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Value;

@Value
@Builder
@Type("gameReviewsSummary")
@EqualsAndHashCode(callSuper = true)
public class GameReviewsSummary extends Review {

  @Relationship("game")
  Game game;
}
