package com.faforever.commons.api.dto;

import com.github.jasminb.jsonapi.annotations.Relationship;
import com.github.jasminb.jsonapi.annotations.Type;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

@Data
@ToString(onlyExplicitlyIncluded = true, callSuper = true)
@EqualsAndHashCode(onlyExplicitlyIncluded = true, callSuper = true)
@Type("leagueSeasonScore")
public class LeagueScoreJournal extends AbstractEntity<LeagueScoreJournal> {
  private Integer gameId;
  private Integer loginId;
  private Integer gameCount;
  private Integer scoreBefore;
  private Integer scoreAfter;

  @Relationship("leagueSeason")
  private LeagueSeason leagueSeason;

  @Relationship("leagueSeasonDivisionSubdivision")
  private LeagueSeasonDivisionSubdivision leagueSeasonDivisionSubdivisionBefore;

  @Relationship("leagueSeasonDivisionSubdivision")
  private LeagueSeasonDivisionSubdivision leagueSeasonDivisionSubdivisionAfter;
}
