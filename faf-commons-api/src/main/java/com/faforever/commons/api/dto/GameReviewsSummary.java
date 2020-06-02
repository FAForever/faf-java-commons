package com.faforever.commons.api.dto;

import com.github.jasminb.jsonapi.annotations.Relationship;
import com.github.jasminb.jsonapi.annotations.Type;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Type("gameReviewsSummary")
@EqualsAndHashCode(callSuper = true)
public class GameReviewsSummary extends Review {

  @Relationship("game")
  private Game game;
}
