package com.faforever.commons.api.dto;

import com.github.jasminb.jsonapi.annotations.Relationship;
import com.github.jasminb.jsonapi.annotations.Type;
import lombok.EqualsAndHashCode;
import lombok.Value;

@Value
@EqualsAndHashCode(callSuper = true)
@Type("leaderboardRating")
public class LeaderboardEntry extends AbstractEntity{

  Double mean;
  Double deviation;
  Integer totalGames;
  Integer wonGames;
  Double rating;

  @Relationship("player")
  Player player;

  @Relationship("leaderboard")
  Leaderboard leaderboard;
}
