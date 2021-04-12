package com.faforever.commons.api.dto;

import com.github.jasminb.jsonapi.annotations.Relationship;
import com.github.jasminb.jsonapi.annotations.Type;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Value;

@Value
@Builder
@EqualsAndHashCode(callSuper = true)
@Type("leaderboardRatingJournal")
public class LeaderboardRatingJournal extends AbstractEntity{

  Double meanAfter;
  Double deviationAfter;
  Double meanBefore;
  Double deviationBefore;

  @Relationship("gamePlayerStats")
  GamePlayerStats gamePlayerStats;

  @Relationship("leaderboard")
  Leaderboard leaderboard;
}
