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
@Type("leagueSeasonDivision")
public class LeagueSeasonDivision {
  @Id
  @ToString.Include
  @EqualsAndHashCode.Include
  private String id;
  private String descriptionKey;
  private Integer divisionIndex;
  @ToString.Include
  private String nameKey;

  @Relationship("leagueSeason")
  private LeagueSeason leagueSeason;
}
