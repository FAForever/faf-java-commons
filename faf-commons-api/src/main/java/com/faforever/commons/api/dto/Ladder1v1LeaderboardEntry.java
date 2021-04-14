package com.faforever.commons.api.dto;

import com.github.jasminb.jsonapi.annotations.Id;
import com.github.jasminb.jsonapi.annotations.Type;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

/**
 * @deprecated Ladder1v1LeaderboardEntry replaced with leaderboardRating
 */
@Deprecated
@Data
@ToString(of = {"id", "name"})
@EqualsAndHashCode(of = "id")
@Type("ladder1v1LeaderboardEntry")
public class Ladder1v1LeaderboardEntry {
  @Id
  private String id;
  private int rank;
  private String name;
  private Double mean;
  private Double deviation;
  private Integer numGames;
  private Integer wonGames;
  private Boolean isActive;
  private Double rating;
}
