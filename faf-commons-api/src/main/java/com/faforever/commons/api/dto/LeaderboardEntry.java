package com.faforever.commons.api.dto;

import com.github.jasminb.jsonapi.annotations.Relationship;
import com.github.jasminb.jsonapi.annotations.Type;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = true)
@Type("leaderboardRating")
public class LeaderboardEntry extends AbstractEntity {
  private Double mean;
  private Double deviation;
  private Integer totalGames;
  private Integer wonGames;
  private Double rating;

  @Relationship("player")
  private Player player;

  @Relationship("leaderboard")
  private Leaderboard leaderboard;
}
