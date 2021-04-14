package com.faforever.commons.api.dto;

import com.github.jasminb.jsonapi.annotations.Relationship;
import com.github.jasminb.jsonapi.annotations.Type;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

@Data
@ToString(of = {"leaderboard", "gamePlayerStats"}, callSuper = true)
@EqualsAndHashCode(callSuper = true)
@Type("leaderboardRatingJournal")
public class LeaderboardRatingJournal extends AbstractEntity{
  private Double meanAfter;
  private Double deviationAfter;
  private Double meanBefore;
  private Double deviationBefore;

  @Relationship("gamePlayerStats")
  private GamePlayerStats gamePlayerStats;

  @Relationship("leaderboard")
  private Leaderboard leaderboard;
}
