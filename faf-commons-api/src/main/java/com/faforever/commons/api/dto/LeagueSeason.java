package com.faforever.commons.api.dto;

import com.github.jasminb.jsonapi.annotations.Id;
import com.github.jasminb.jsonapi.annotations.Type;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

import java.time.OffsetDateTime;

@Data
@ToString(onlyExplicitlyIncluded = true)
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@Type("leagueSeason")
public class LeagueSeason {
  @Id
  @ToString.Include
  @EqualsAndHashCode.Include
  private String id;
  private Integer leagueId;
  private Integer leaderboardId;
  @ToString.Include
  private String technicalName; // not provided yet
  private OffsetDateTime startDate;
  private OffsetDateTime endDate;
}
