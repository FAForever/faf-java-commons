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
@Type("leagueSeasonDivisionSubdivision")
public class LeagueSeasonDivisionSubdivision {
  @Id
  @ToString.Include
  @EqualsAndHashCode.Include
  private String id;
  private String descriptionKey;
  private Integer highestScore;
  private Integer maxRating;
  private Integer minRating;
  @ToString.Include
  private String nameKey;
  private Integer subdivisionIndex;

  @Relationship("leagueSeasonDivision")
  private LeagueSeasonDivision leagueSeasonDivision;
}
