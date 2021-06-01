package com.faforever.commons.api.dto;

import com.github.jasminb.jsonapi.annotations.Id;
import com.github.jasminb.jsonapi.annotations.Relationship;
import com.github.jasminb.jsonapi.annotations.Type;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

@Data
@ToString(onlyExplicitlyIncluded = true)
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@Type("leagueLeaderboard")
public class LeagueEntry {
  @Id
  @ToString.Include
  @EqualsAndHashCode.Include
  private String id;
  private Integer rank; // Not supported at the moment
  private Integer score; // (may be null)
  private Integer numGames;
  private Integer wonGames; // Not supported at the moment
  //server only stores subDivisionId instead of explicitly these two (may be null)
  private Integer majorDivisionIndex;
  private Integer subDivisionIndex;

  @Relationship("player")
  @ToString.Include
  private Player player;

  @Relationship("league")
  @ToString.Include
  private LeagueSeason leagueSeason;
}
