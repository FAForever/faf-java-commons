package com.faforever.commons.api.dto;

import com.github.jasminb.jsonapi.annotations.Id;
import com.github.jasminb.jsonapi.annotations.Type;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@Type("leagueLeaderboard")
public class LeagueLeaderboard {
  @Id
  @EqualsAndHashCode.Include
  private String id;
  private String technicalName;
}
